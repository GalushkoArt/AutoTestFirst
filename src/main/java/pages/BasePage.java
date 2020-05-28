package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public abstract class BasePage {
    protected static String linkWithTextSelector = ".//a[text()=\"%s\"]";

    /**
     * opens current user home page
     * @return new PersonPage
     */

    public PersonPage openHomePage() {
        Selenide.open("/");
        return new PersonPage();
    }

    /**
     * clicks on element with locator
     * @param locator Selenium By locator
     * @return current page
     */

    protected BasePage hover(By locator) {
        waitUntilShows(locator);
        $(locator).hover();
        return this;
    }

    protected BasePage click(By locator) {
        waitUntilShows(locator);
        $(locator).click();
        return this;
    }

    /**
     * types text to element with locator
     * @param locator Selenium By locator
     * @param text text for searching
     * @return current page
     */

    protected BasePage type(By locator, String text) {
        waitUntilShows(locator);
        $(locator).sendKeys(text);
        return this;
    }

    public BasePage sendKey(By locator, Keys key) {
        waitUntilShows(locator);
        $(locator).hover()
                .sendKeys(key);
        return this;
    }

    /**
     * clears field of element with locator
     * @param locator Selenium By locator
     * @return current page
     */

    protected BasePage clearField(By locator) {
        waitUntilShows(locator);
        $(locator).clear();
        return this;
    }

    protected String getText(By locator) {
        waitUntilShows(locator);
        return $(locator).getText();
    }

    protected BasePage scrollToElement(By locator) {
        waitUntilShows(locator);
        $(locator).scrollTo();
        return this;
    }

    /**
     * checks if element with locator is displayed
     * @param locator Selenium By locator
     */

    protected boolean isElementDisplayed(By locator) {
        waitUntilShows(locator);
        return $(locator).isDisplayed();
    }

    /**
     * returns list of element with locator
     * @param locator Selenium By locator
     * @return ElementsCollection
     */

    protected ElementsCollection getListOfElements(By locator) {
        return $$(locator);
    }

    /**
     * waits up to 10 seconds until element with locator disappear
     * @param locator Selenium By locator
     * @return current page
     */

    protected BasePage waitUntilDisappear(By locator) {
        $(locator).waitUntil(disappear, 10000, 400);
        return this;
    }

    /**
     * waits up to seconds until element with locator disappear
     * @param locator Selenium By locator
     * @param seconds maximum waiting time
     * @return current page
     */

    protected BasePage waitUntilDisappear(By locator, int seconds) {
        $(locator).waitUntil(disappear, 1000*seconds, 400);
        return this;
    }


    /**
     * waits up to 5 seconds until element with locator shows
     * @param locator Selenium By locator
     * @return current page
     */

    protected BasePage waitUntilShows(By locator) {
        $(locator).waitUntil(appear, 5000, 400);
        return this;
    }


    /**
     * waits up to seconds until element with locator shows
     * @param locator Selenium By locator
     * @param seconds maximum waiting time
     * @return current page
     */

    protected BasePage waitUntilShows(By locator, int seconds) {
        $(locator).waitUntil(appear, 1000*seconds, 400);
        return this;
    }

    /**
     * returns link element with text
     * @param text text for searching
     * @return SelenideElement
     */

    protected SelenideElement findLinkWithText(String text) {
        return $x(format(linkWithTextSelector, text));
    }

    protected BasePage chooseFirstElementInList(By element, By list) {
        $(list).findAll(element)
                .first()
                .click();
        return this;
    }
}
