package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonPage extends HelperBase {
    By friendshipButton = By.xpath(".//*[@data-l=\"t,invite\"]");

    public PersonPage(WebDriver driver) {
        super(driver);
    }

    public void addToFriends() {
        click(friendshipButton);
    }

    public boolean isInvitedToFriends() {
        return isElementPresent(By.xpath(".//*[@data-l='t,invite']//*[contains(@class, 'svg-ico_check')]"));
    }

    public void revokeInvite() {
        click(friendshipButton);
        click(By.xpath(".//a[contains(@href, \"act=REVOKE\")]"));
    }

    public void goToFriends() {
        click(By.xpath(".//*[contains(@hrefattrs, \"NavMenu_Friend_Friends\")]"));
    }
}
