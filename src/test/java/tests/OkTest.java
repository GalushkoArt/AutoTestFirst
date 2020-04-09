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

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import java.util.List;

import static pages.PersonFactory.getNewPersonFactory;


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

        clickOnFirstFriends();
        getNewPersonFactory(driver).goToFriends();
        clickOnFirstFriends();

        PersonPage friendPage = getNewPersonFactory(driver);
        friendPage.addToFriends();
        Assertions.assertTrue(friendPage.isInvitedToFriends());
    }

    private void clickOnFirstFriends() {
        new FriendsPage(driver).getListOfMyFriends().get(0).click();

//        new FriendsPage(driver)
//                .getListOfFriendsName()
//                .stream()
//                .filter() // нужно найти тег по которому отсеим себя и тех кто уже нам друг
//                .findFirst()
//                .isPresent;
    }

    @Test
    public void revokeFriendship() {
        driver.navigate().refresh();
        PersonPage currentPage = new PersonPage(driver);
        currentPage.revokeInvite();
        Assertions.assertTrue(currentPage.isInvitedToFriends());
    }
}
