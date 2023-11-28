package page;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class StartPage {
    private SelenideElement buttonBuy = $(byText("Купить"));
    private SelenideElement buttonBuyByCredit = $(byText("Купить в кредит"));
    private SelenideElement byCard = $(byText("Оплата по карте"));
    private SelenideElement byCredit = $(byText("Кредит по данным карты"));

    public OrderCardPage buyWithoutCredit() {
        buttonBuy.click();
        byCard.shouldBe(visible);
        return new OrderCardPage();
    }

    public CreditPage buyWithCredit() {
        buttonBuyByCredit.click();
        byCredit.shouldBe(visible);
        return new CreditPage();
    }
}
