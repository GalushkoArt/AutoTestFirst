package tests;

import org.junit.jupiter.api.Test;
import pages.PersonPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FriendshipTests extends TestBase {
    @Test
    public void friendshipInviteTest() {
        final String fFirstName = "Данила";
        final String fLastName = "Федоров";
        final String ffFirstName = "Артём";
        final String ffLastName = "Галушко";


        PersonPage currentPage =  new PersonPage()
                .goToMenuFriends()
                .clickOnPersonWithName(fFirstName, fLastName)
                .goToFriends()
                .clickOnPersonWithName(ffFirstName, ffLastName)
                .addToFriends();

        assertTrue(currentPage.isInvitedToFriends());

        new PersonPage().revokeInvite();    //Сброс
    }

    @Test
    public void revokeFriendshipTest() {
        final String pFirstName = "technopolisBot4";
        final String pLastName = "technopolisBot4";


        PersonPage currentPage =  new PersonPage();
        currentPage.openHomePage()
                .goToMenuFriends()
                .goToSentRequests()
                .clickOnPersonWithName(pFirstName, pLastName)
                .revokeInvite();

        assertFalse(currentPage.isInvitedToFriends());

        new PersonPage().addToFriends();    //Сброс
    }
}
