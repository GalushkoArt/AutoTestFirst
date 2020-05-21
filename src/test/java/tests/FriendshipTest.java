package tests;

import org.junit.jupiter.api.Test;
import pages.PersonPage;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FriendshipTest extends TestBase {
    @Test
    public void friendshipInviteTest() {
        PersonPage currentPage =  new PersonPage()
                .goToMenuFriends()
                .clickOnPersonWithName("Данила", "Федоров")
                .goToFriends()
                .clickOnPersonWithName("Артём", "Галушко")
                .addToFriends();

        assertFalse(currentPage.isInvitedToFriends());

        refresh(); //Сброс
        new PersonPage().revokeInvite();
    }

    @Test
    public void revokeFriendshipTest() {
        PersonPage currentPage =  new PersonPage();
        currentPage.openHomePage()
                .goToMenuFriends()
                .goToSentRequests()
                .clickOnPersonWithName("technopolisBot4","technopolisBot4")
                .revokeInvite();

        assertTrue(currentPage.isInvitedToFriends());

        refresh(); //Сброс
        new PersonPage().addToFriends();
    }
}
