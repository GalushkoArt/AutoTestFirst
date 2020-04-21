package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

public class FriendsPage extends HelperBase {

    public static final By FRIEND_LIST = By.xpath(".//ul[@class = \"ugrid_cnt\"]//li[@class=\"ugrid_i\"]");
    public static final By FRIENDS_NAME = By.xpath(".//*[@class = \"ugrid_cnt\"]//*[@class=\"ucard-w_t ellip-i\"]");
    public static final By SENT_REQUESTS_PAGE = By.xpath(".//a[contains(@href, 'outgoingFriendRequests')]");
    public static final By FRIENDS_PAGE = By.cssSelector("div.friends-page");

    /**
     * returns list of SelenideElement of user's friends
     * @return ElementsCollection
     */

    public ElementsCollection getListOfMyFriends() {
        return getListOfElements(FRIEND_LIST);
    }

    /**
     * returns list of SelenideElement of person's friends
     * @return ElementsCollection
     */

    public ElementsCollection getListOfFriendsName() {
        return getListOfElements(FRIENDS_NAME);
    }

    /**
     * goes to sent requests section
     * @return current FriendsPage
     */

    public FriendsPage goToSentRequests() {
        click(SENT_REQUESTS_PAGE);
        return this;
    }

    /**
     * clicks on link with with first name and last name
     * @param firstName person first name
     * @param LastName person last name
     * @return new PersonPage
     */

    public PersonPage clickOnPersonWithName(String firstName, String LastName) {
        findLinkWithText(firstName + " " + LastName).click();
        waitUntilShows(PersonPage.USER_PHOTO);
        return new PersonPage();
    }
}
