package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public abstract class HelperBase {
    protected static String linkWithTextSelector = ".//a[text()=\"%s\"]";

    public PersonPage openHomePage() {
        Selenide.open("/");
        return new PersonPage();
    }

    protected HelperBase click(By locator) {
        $(locator).click();
        return this;
    }

    protected HelperBase type(By locator, String text) {
        $(locator).sendKeys(text);
        return this;
    }

    protected HelperBase clearField(By locator) {
        $(locator).clear();
        return this;
    }

    protected boolean isElementDisplayed(By locator) {
        return $(locator).isDisplayed();
    }

    protected ElementsCollection getListOfElements(By locator) {
        return $$(locator);
    }

    protected HelperBase waitUntilDisappear(By locator) {
        $(locator).waitUntil(disappear, 10000, 400);
        return this;
    }

    protected HelperBase waitUntilDisappear(By locator, int seconds) {
        $(locator).waitUntil(disappear, 1000*seconds, 400);
        return this;
    }

    protected HelperBase waitUntilShows(By locator) {
        $(locator).waitUntil(appear, 5000, 400);
        return this;
    }

    protected HelperBase waitUntilShows(By locator, int seconds) {
        $(locator).waitUntil(appear, 1000*seconds, 400);
        return this;
    }

    protected SelenideElement findLinkWithText(String text) {
        return $x(format(linkWithTextSelector, text));
    }
}
