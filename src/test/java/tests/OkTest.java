package tests;

import model.BotFactory;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.*;


import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class OkTest extends TestBase {
    @BeforeAll
    public static void initTest() {
        baseUrl = "https://ok.ru/";
    }

    @Test
    public void checkFriend() {
        bot = BotFactory.getOkBot();
        new LoginPage().openHomePage();
        PersonPage currentPage =  new LoginPage().logIn(bot)
                .goToMenuFriends()
                .clickOnPersonWithName("Данила", "Федоров")
                .goToFriends()
                .clickOnPersonWithName("Артём", "Галушко")
                .addToFriends();

        assertFalse(currentPage.isInvitedToFriends());
        refresh();
        new PersonPage().revokeInvite();
    }

    @After
    public void revokeInvite() {
        //api revoking
    }

    @Test
    public void revokeFriendship() {
        PersonPage currentPage =  new PersonPage();
        currentPage.openHomePage()
                .goToMenuFriends()
                .goToSentRequests()
                .clickOnPersonWithName("technopolisBot4","technopolisBot4")
                .revokeInvite();

        assertTrue(currentPage.isInvitedToFriends());
        refresh();
        new PersonPage().addToFriends();
    }

    @After
    public void restoreInvite() {
        //api inviting
    }
}
