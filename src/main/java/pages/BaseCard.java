package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public abstract class BaseCard {
    protected SelenideElement element;

    protected BaseCard(SelenideElement element) {
        this.element = element;
    }

    public BaseCard hover() {
        element.hover();
        return this;
    }

    public BaseCard hover(By locator) {
        element.$(locator).hover();
        return this;
    }

    public BaseCard click(By locator) {
        element.$(locator).waitUntil(appear, 5000, 400);
        element.$(locator)
                .hover()
                .click();
        return this;
    }

    public BaseCard confirm(By locator) {
        $(locator).waitUntil(appear, 5000, 400);
        $(locator)
                .hover()
                .click();
        return this;
    }

    public BaseCard type(By locator, String text) {
        element.$(locator).waitUntil(appear, 5000, 400);
        element.$(locator).hover()
                .sendKeys(text);
        return this;
    }

    public boolean isExists(By locator) {
        element.$(locator).waitUntil(appear, 5000, 400);
        return element.$(locator).exists();
    }

    public void verify(By locator) {
        element.$(locator).waitUntil(appear, 5000, 400);
        element.$(locator).shouldBe(visible);
    }

    public void verify(By parent, By locator) {
        element.$(locator).waitUntil(appear, 5000, 400);
        element.$(parent).$(locator)
                .shouldBe(visible);
    }

    public String getText(By locator) {
        return element.$(locator).getText();
    }
}
