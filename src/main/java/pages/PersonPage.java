package pages;

import org.openqa.selenium.By;

public class PersonPage extends HelperBase {
    private static final By FRIENDSHIP_BUTTON = By.xpath(".//*[@data-l=\"t,invite\"]");
    private static final By REQUEST_BUTTON = By.xpath(".//div[contains(@class, \"dropdown h-mod\")]");
    private static final By REVOKED_FRIENDSHIP = By.xpath(".//a[contains(@href, \"act=REVOKE\")]");
    public static final By USER_PHOTO = By.cssSelector("div.entity-avatar");
    public static final By FREAND_LIST = By.xpath(".//*[contains(@hrefattrs, \"NavMenu_Friend_Friends\")]");
    public static final By INVITE_FRIEANDS = By.xpath(".//*[@data-l='t,invite']//*[contains(@class, 'svg-ico_check')]");
    public static final By GO_TO_FREAND_PAGE = By.xpath(".//*[@class=\"toolbar_nav\"]/*[@data-l=\"t,friends\"]");

    public FriendsPage goToMenuFriends() {
        click(GO_TO_FREAND_PAGE);
        waitUntilShows(FriendsPage.FRIENDS_PAGE);
        return new FriendsPage();
    }

    public PersonPage addToFriends() {
        click(FRIENDSHIP_BUTTON);
        return this;
    }

    public boolean isInvitedToFriends() {
        return isElementDisplayed(INVITE_FRIEANDS);
    }

    public PersonPage revokeInvite() {
        click(REQUEST_BUTTON);
        waitUntilShows(REVOKED_FRIENDSHIP);
        click(REVOKED_FRIENDSHIP);
        return this;
    }

    public FriendsPage goToFriends() {
        click(FREAND_LIST);
        waitUntilShows(FriendsPage.FRIENDS_PAGE);
        return new FriendsPage();
    }
}
