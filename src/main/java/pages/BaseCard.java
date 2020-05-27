package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
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
        waitUntilShows(locator);
        element.$(locator)
                .hover()
                .click();
        return this;
    }

    public BaseCard confirm(By locator) {
        waitUntilShows(locator);
        $(locator)
                .hover()
                .click();
        return this;
    }

    public BaseCard type(By locator, String text) {
        waitUntilShows(locator);
        element.$(locator).hover()
                .sendKeys(text);
        return this;
    }

    public BaseCard sendKey(By locator, Keys key) {
        waitUntilShows(locator);
        element.$(locator).hover()
                .sendKeys(key);
        return this;
    }

    public boolean isExists(By locator) {
        waitUntilShows(locator);
        return element.$(locator).exists();
    }

    public BaseCard waitUntilShows(By locator) {
        $(locator).waitUntil(appear, 5000, 400);
        return this;
    }

    public BaseCard waitUntilDisappear(By locator) {
        $(locator).waitUntil(disappear, 5000, 400);
        return this;
    }

    public void verify(By locator) {
        waitUntilShows(locator);
        element.$(locator)
                .shouldBe(visible);
    }

    public void verify(By parent, By locator) {
        waitUntilShows(locator);
        element.$(parent)
                .$(locator)
                .shouldBe(visible);
    }

    public String getText(By locator) {
        waitUntilShows(locator);
        return element.$(locator).getText();
    }
}
