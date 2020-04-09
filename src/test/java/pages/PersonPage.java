package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonPage extends HelperBase {
    private static final By FRIENDSHIP_BUTTON = By.xpath(".//*[@data-l=\"t,invite\"]");
    private static final By REQUEST_BUTTON = By.xpath(".//div[contains(@class, \"dropdown h-mod\")]");
    private static final By REVOKED_FRIENDSHIP = By.xpath(".//a[contains(@href, \"act=REVOKE\")]");
    public static final By FREAND_LIST = By.xpath(".//*[contains(@hrefattrs, \"NavMenu_Friend_Friends\")]");
    public static final By INVITE_FRIEANDS = By.xpath(".//*[@data-l='t,invite']//*[contains(@class, 'svg-ico_check')]");

    public PersonPage(WebDriver driver) { super(driver); }

    public void addToFriends() {
        click(FRIENDSHIP_BUTTON);
    }

    public boolean isInvitedToFriends() {
        return isElementPresent(INVITE_FRIEANDS);
    }

    public void revokeInvite() {
        click(REQUEST_BUTTON);
        if(explicitWait(
                ExpectedConditions.visibilityOfNestedElementsLocatedBy(
                        REQUEST_BUTTON,
                        REVOKED_FRIENDSHIP
                ),
                5,
                500))
            click(REVOKED_FRIENDSHIP);
    }

    public void goToFriends() {
        click(FREAND_LIST);
    }
}
