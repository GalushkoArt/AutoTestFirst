package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

public class FriendsPage extends HelperBase {

    public static final By FRIEND_LIST = By.xpath(".//ul[@class = \"ugrid_cnt\"]//li[@class=\"ugrid_i\"]");
    public static final By FRIENDS_NAME = By.xpath(".//*[@class = \"ugrid_cnt\"]//*[@class=\"ucard-w_t ellip-i\"]");
    public static final By SENT_REQUESTS_PAGE = By.xpath(".//a[contains(@href, 'outgoingFriendRequests')]");
    public static final By FRIENDS_PAGE = By.cssSelector("div.friends-page");

    public ElementsCollection getListOfMyFriends() {
        return getListOfElements(FRIEND_LIST);
    }

    public ElementsCollection getListOfFriendsName() {
        return getListOfElements(FRIENDS_NAME);
    }

    public FriendsPage goToSentRequests() {
        click(SENT_REQUESTS_PAGE);
        return this;
    }

    public PersonPage clickOnPersonWithName(String firstName, String LastName) {
        findLinkWithText(firstName + " " + LastName).click();
        waitUntilShows(PersonPage.USER_PHOTO);
        return new PersonPage();
    }
}
