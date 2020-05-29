package pages;

import factory.FriendsPageFactory;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonPage extends BasePage {
    private static final By FRIENDSHIP_BUTTON = By.xpath(".//*[@data-l=\"t,invite\"]");
    private static final By REQUEST_BUTTON = By.xpath(".//div[contains(@class, \"dropdown h-mod\")]");
    private static final By REVOKED_FRIENDSHIP = By.xpath(".//a[contains(@href, \"act=REVOKE\")]");
    public static final By USER_PHOTO = By.cssSelector("div.entity-avatar");
    public static final By FREAND_LIST = By.xpath(".//*[contains(@hrefattrs, \"NavMenu_Friend_Friends\")]");
    public static final By INVITE_FRIEANDS = By.xpath(".//li[@data-l='t,invite']//div[contains(@class, 'dropdown')]");
    public static final By GO_TO_FREAND_PAGE = By.xpath(".//*[@class=\"toolbar_nav\"]/*[@data-l=\"t,friends\"]");

    private static final Logger logger = LoggerFactory.getLogger(PersonPage.class);

    /**
     * goes to Friends by navigation menu
     * @return new FriendsPage
     */

    public MyFriendsPage goToMenuFriends() {
        click(GO_TO_FREAND_PAGE);
        waitUntilShows(FriendsPage.FRIENDS_PAGE);
        logger.info("Went to person friends page");
        return new MyFriendsPage();
    }

    /**
     * clicks on button add to friends
     * @return current PersonPage
     */

    public PersonPage addToFriends() {
        click(FRIENDSHIP_BUTTON);
        waitUntilShows(REVOKED_FRIENDSHIP);
        logger.info("Added to friends");
        return this;
    }

    /**
     *checks if person is invited to friends
     */

    public boolean isInvitedToFriends() {
        logger.info("Asked if person is invited to friends");
        return isElementDisplayed(INVITE_FRIEANDS);
    }

    /**
     * clicks on button revoke invite
     * @return current PersonPage
     */

    public PersonPage revokeInvite() {
        click(REQUEST_BUTTON);
        waitUntilShows(REVOKED_FRIENDSHIP);
        click(REVOKED_FRIENDSHIP);
        waitUntilDisappear(REVOKED_FRIENDSHIP);
        logger.info("Friendship invite is revoked");
        return this;
    }

    /**
     * goes to person's friends
     * @return new FriendsPage
     */

    public BaseFriendsPage goToFriends() {
        click(FREAND_LIST);
        waitUntilShows(FriendsPage.FRIENDS_PAGE);
        logger.info("Went to persons friends page");
        return FriendsPageFactory.getFriendsPage();
    }
}