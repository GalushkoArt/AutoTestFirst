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

    protected HelperBase hover(By locator) {
        $(locator).hover();
        return this;
    }

    protected HelperBase click(By locator) {
        $(locator).click();
        return this;
    }

    /**
     * types text to element with locator
     * @param locator Selenium By locator
     * @param text text for searching
     * @return current page
     */

    protected HelperBase type(By locator, String text) {
        $(locator).sendKeys(text);
        return this;
    }

    /**
     * clears field of element with locator
     * @param locator Selenium By locator
     * @return current page
     */

    protected HelperBase clearField(By locator) {
        $(locator).clear();
        return this;
    }

    protected String getText(By locator) {
        return $(locator).getText();
    }

    protected HelperBase scrollToElement(By locator) {
        $(locator).scrollTo();
        return this;
    }

    /**
     * checks if element with locator is displayed
     * @param locator Selenium By locator
     */

    protected boolean isElementDisplayed(By locator) {
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

    protected HelperBase waitUntilDisappear(By locator) {
        $(locator).waitUntil(disappear, 10000, 400);
        return this;
    }

    /**
     * waits up to seconds until element with locator disappear
     * @param locator Selenium By locator
     * @param seconds maximum waiting time
     * @return current page
     */

    protected HelperBase waitUntilDisappear(By locator, int seconds) {
        $(locator).waitUntil(disappear, 1000*seconds, 400);
        return this;
    }


    /**
     * waits up to 5 seconds until element with locator shows
     * @param locator Selenium By locator
     * @return current page
     */

    protected HelperBase waitUntilShows(By locator) {
        $(locator).waitUntil(appear, 5000, 400);
        return this;
    }


    /**
     * waits up to seconds until element with locator shows
     * @param locator Selenium By locator
     * @param seconds maximum waiting time
     * @return current page
     */

    protected HelperBase waitUntilShows(By locator, int seconds) {
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

    protected HelperBase chooseFirstElementInList(By element, By list) {
        $(list).findAll(element)
                .first()
                .click();
        return this;
    }
}
