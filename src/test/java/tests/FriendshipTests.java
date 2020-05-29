package tests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.PersonPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FriendshipTests extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(FriendshipTests.class);

    @Test
    public void friendshipInviteTest() {
        final String F_FIRST_NAME = "Данила";
        final String F_LAST_NAME = "Федоров";
        final String FF_FIRST_NAME = "Артём";
        final String FF_LAST_NAME = "Галушко";

        logger.info("Run friendship invite test");

        PersonPage currentPage =  new PersonPage()
                .goToMenuFriends()
                .clickOnPersonWithName(F_FIRST_NAME, F_LAST_NAME)
                .goToFriends()
                .clickOnPersonWithName(FF_FIRST_NAME, FF_LAST_NAME)
                .addToFriends();

        assertTrue(currentPage.isInvitedToFriends());
        logger.info("Friendship invite test successful");

        new PersonPage().revokeInvite();    //Сброс
        logger.info("Test restored");
    }

    @Test
    public void revokeFriendshipTest() {
        final String P_FIRST_NAME = "technopolisBot4";
        final String P_LAST_NAME = "technopolisBot4";

        logger.info("Run revoke friendship invite test");

        PersonPage currentPage = new PersonPage();
        currentPage.openHomePage()
                .goToMenuFriends()
                .goToSentRequests()
                .clickOnPersonWithName(P_FIRST_NAME, P_LAST_NAME)
                .revokeInvite();

        assertFalse(currentPage.isInvitedToFriends());
        logger.info("Revoke friendship invite test successful");

        new PersonPage().addToFriends();    //Сброс
        logger.info("Test restored");
    }
}
