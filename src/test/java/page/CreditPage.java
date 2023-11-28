package page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private SelenideElement fieldCardNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement cardSize = $("[maxLength='19']");
    private SelenideElement fieldMonth = $("[placeholder='08']");
    private SelenideElement fieldYear = $("[placeholder='22']");
    private SelenideElement fieldOwner = $$("[class='input__control']").get(3);
    private SelenideElement fieldCvc = $("[placeholder='999']");
    private SelenideElement buttonContinue = $(byText("Продолжить"));

    private SelenideElement bankApproved = $(withText("Операция одобрена Банком."));
    private SelenideElement bankRefusal = $(withText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement errorFormat = $(withText("Неверный формат"));
    private SelenideElement invalidDurationCard = $(withText("Неверно указан срок действия карты"));
    private SelenideElement cardExpired = $(withText("Истёк срок действия карты"));
    private SelenideElement requiredField = $(withText("Поле обязательно для заполнения"));

    public void fillOutFields(String cardNumber, String month, String year, String owner, String cvc) {
        fieldCardNumber.setValue(cardNumber);
        fieldMonth.setValue(month);
        fieldYear.setValue(year);
        fieldOwner.setValue(owner);
        fieldCvc.setValue(cvc);
        buttonContinue.click();
    }

    public void expectApprovalFromBank() {
        bankApproved.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void expectRejectionFromBank() {
        bankRefusal.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitInvalidFormat() {
        errorFormat.shouldBe(visible);
    }

    public void waitNecessaryFillOutField() {
        requiredField.shouldBe(visible);
    }

    public void waitInvalidDuration() {
        invalidDurationCard.shouldBe(visible);
    }

    public void waitInvalidYear() {
        cardExpired.shouldBe(visible);
    }
}