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

    //todo эта штука нормально не работает после отзыва дружбы
    public boolean isInvitedToFriends() {
        return isElementPresent(friendshipButton);
    }

    public void revokeInvite() {
        click(friendshipButton);
        click(By.xpath(".//a[contains(@href, \"act=REVOKE\")]"));
    }

    public void goToFriends() {
        click(By.xpath(".//*[contains(@hrefattrs, \"NavMenu_Friend_Friends\")]"));
    }
}
