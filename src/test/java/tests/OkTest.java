package tests;

import model.BotFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.FriendsPage;
import pages.LoginPage;
import pages.PersonPage;
import pages.UserMainPage;

import java.util.List;


public class OkTest extends TestBase {
    @BeforeAll
    public static void initTest() {
        baseUrl = "https://ok.ru/";
        driver.get(baseUrl);
    }

    @Test
    public void checkFriend() {
        bot = BotFactory.getOkBot();
        new LoginPage(driver).logIn(bot);
        new UserMainPage(driver).goToPageFriends();
        List<WebElement> friends = new FriendsPage(driver).getListOfMyFriends();
        friends.get(0).click();
        new PersonPage(driver).goToFriends();
        friends = new FriendsPage(driver).getListOfFriendsName();
        friends.get(0).click();
        PersonPage friendPage = new PersonPage(driver);
        friendPage.addToFriends();
        Assertions.assertTrue(friendPage.isInvitedToFriends());
    }

    @Test
    public void revokeFriendship() {
        driver.navigate().refresh();
        PersonPage currentPage = new PersonPage(driver);
        currentPage.revokeInvite();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
        Assertions.assertFalse(currentPage.isInvitedToFriends());
    }
}
