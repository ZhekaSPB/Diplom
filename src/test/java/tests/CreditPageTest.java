package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataBaseHelper;
import data.DataHelp;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.CreditPage;
import page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditPageTest {
    StartPage startPage;
    CreditPage creditPage;

    @BeforeEach
    void shouldCleanDataBaseOpenWeb() {
        DataBaseHelper.cleanDataBase();
        startPage = open("http://localhost:8080", StartPage.class);
        creditPage = startPage.buyWithCredit();
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
    void shouldSecondCard() {

        var cardNumber = DataHelp.getSecondCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        creditPage.fillOutFields(cardNumber, month, year, owner, cvc);
        creditPage.expectRejectionFromBank();
        var expected = DataHelp.getSecondCardExpectedStatus();
        var actual = DataBaseHelper.getStatusPaymentWithCredit();
        assertEquals(expected, actual);

    }

    @Test
    void shouldRejectZeroCvc() { /*Кредит по данным карты при вводе нулевого cvc*/
        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getZeroCvv();
        creditPage.fillOutFields(cardNumber, month, year, owner, cvc);
        creditPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectMonth() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getPastMonth();
        var year = DataHelp.getThisYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        creditPage.fillOutFields(cardNumber, month, year, owner, cvc);
        creditPage.waitInvalidDuration();

    }

    @Test
    void checkingMonthLimit() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getDurationOverLimit();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        creditPage.fillOutFields(cardNumber, month, year, owner, cvc);
        creditPage.waitInvalidDuration();

    }

    @Test
    void checkingOwRus() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getInvalidOwnerRus();
        var cvc = DataHelp.getValidCvc();
        creditPage.fillOutFields(cardNumber, month, year, owner, cvc);
        creditPage.waitInvalidFormat();

    }

    @Test
    void shouldAppFirstCard() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        creditPage.fillOutFields(cardNumber, month, year, owner, cvc);
        creditPage.expectApprovalFromBank();
        var expected = DataHelp.getFirstCardExpectedStatus();
        var actual = DataBaseHelper.getStatusPaymentWithCredit();
        assertEquals(expected, actual);

    }

    @Test
    void shouldEmptyNumberCard() {

        var cardNumber = DataHelp.getEmptyValue();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        creditPage.fillOutFields(cardNumber, month, year, owner, cvc);
        creditPage.waitNecessaryFillOutField();

    }

    @Test
    void shouldRejectEmpCvc() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getValidYear();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getEmptyValue();
        creditPage.fillOutFields(cardNumber, month, year, owner, cvc);
        creditPage.waitNecessaryFillOutField();

    }

    @Test
    void shouldRejectYear() {

        var cardNumber = DataHelp.getFirstCardNumber();
        var month = DataHelp.getValidMonth();
        var year = DataHelp.getZeroValue();
        var owner = DataHelp.getValidOwner();
        var cvc = DataHelp.getValidCvc();
        creditPage.fillOutFields(cardNumber, month, year, owner, cvc);
        creditPage.waitInvalidFormat();

    }
}
