package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataBaseHelper;
import data.DataHelp;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.OrderCardPage;
import page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCardPageTest {
    StartPage startPage;
    OrderCardPage orderCardPage;

    @BeforeEach
    void shouldOpenWeb() {
        DataBaseHelper.cleanDataBase();
        startPage = open("http://localhost:8080", StartPage.class);
        orderCardPage = startPage.buyWithoutCredit();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }


    @Test
    void shouldApFirstCard() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        orderCardPage.fillOutFields(cardNumber, month, year, owner, cvc);
        orderCardPage.expectApprovalFromBank();
        var expected = DataHelp.getFirstCardExpectedStatus();
        var actual = DataBaseHelper.getStatusPaymentWithoutCredit();
        assertEquals(expected, actual);

    }

    @Test
    void checkingCVVWitText() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValueText();
        orderCardPage.fillOutFields(cardNumber, month, year, owner, cvc);
        orderCardPage.waitInvalidFormat();

    }

    @Test
    void checkingIncompData() {

        var cardNumber = DataHelp.getCardNumberIncomplete();
        var month = DataHelp.getIncompleteMonth();
        var year = DataHelp.getIncompleteYear();
        var owner = DataHelp.getIncompleteOwner();
        var cvc = DataHelp.getIncompleteCvc();
        orderCardPage.fillOutFields(cardNumber, month, year, owner, cvc);
        orderCardPage.waitNecessaryFillOutField();

    }

    @Test
    void shouldRejectInYear() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getInvalidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        orderCardPage.fillOutFields(cardNumber, month, year, owner, cvc);
        orderCardPage.waitInvalidYear();

    }

    @Test
    void shouldRejectSecCard() {

        var cardNumber = DataHelp.getSecondCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        orderCardPage.fillOutFields(cardNumber, month, year, owner, cvc);
        orderCardPage.expectRejectionFromBank();
        var expected = DataHelp.getSecondCardExpectedStatus();
        var actual = DataBaseHelper.getStatusPaymentWithoutCredit();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void shouldRejectInMonth() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getInvalidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        orderCardPage.fillOutFields(cardNumber, month, year, owner, cvc);
        orderCardPage.waitInvalidDuration();

    }

    @Test
    void shouldRejectZeCvc() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getZeroCvv();
        orderCardPage.fillOutFields(cardNumber, month, year, owner, cvc);
        orderCardPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectMonth() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getPastMonth();
        var year = DataHelp.getThisYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        orderCardPage.fillOutFields(cardNumber, month, year, owner, cvc);
        orderCardPage.waitInvalidYear();

    }

    @Test
    void checkingOwRus() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getInvalidOwnerRus();
        var cvc = DataHelp.getValidCvc();
        orderCardPage.fillOutFields(cardNumber, month, year, owner, cvc);
        orderCardPage.waitInvalidFormat();

    }

    @Test
    void checkingCardNumberReqData() {

        var cardNumber = DataHelp.getCardNumberNotExisting();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        orderCardPage.fillOutFields(cardNumber, month, year, owner, cvc);
        orderCardPage.expectRejectionFromBank();

    }

}
