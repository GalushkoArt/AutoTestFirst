package tests;

import org.junit.jupiter.api.Test;
import pages.PersonPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FriendshipTests extends TestBase {
    @Test
    public void friendshipInviteTest() {
        final String F_FIRST_NAME = "Данила";
        final String F_LAST_NAME = "Федоров";
        final String FF_FIRST_NAME = "Артём";
        final String FF_LAST_NAME = "Галушко";


        PersonPage currentPage =  new PersonPage()
                .goToMenuFriends()
                .clickOnPersonWithName(F_FIRST_NAME, F_LAST_NAME)
                .goToFriends()
                .clickOnPersonWithName(FF_FIRST_NAME, FF_LAST_NAME)
                .addToFriends();

        assertTrue(currentPage.isInvitedToFriends());

        new PersonPage().revokeInvite();    //Сброс
    }

    @Test
    public void revokeFriendshipTest() {
        final String P_FIRST_NAME = "technopolisBot4";
        final String P_LAST_NAME = "technopolisBot4";


        PersonPage currentPage = new PersonPage();
        currentPage.openHomePage()
                .goToMenuFriends()
                .goToSentRequests()
                .clickOnPersonWithName(P_FIRST_NAME, P_LAST_NAME)
                .revokeInvite();

        assertFalse(currentPage.isInvitedToFriends());

        new PersonPage().addToFriends();    //Сброс
    }
}
