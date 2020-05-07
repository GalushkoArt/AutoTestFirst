package tests;

import model.BotFactory;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pages.*;


import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.*;


public class OkTest extends TestBase {
    @BeforeAll
    public static void initTest() {
        baseUrl = "https://ok.ru/";
    }

    @Test
    public void friendshipInviteTest() {
        bot = BotFactory.getOkBot();
        new LoginPage().openHomePage();
        PersonPage currentPage =  new LoginPage().logIn(bot)
                .goToMenuFriends()
                .clickOnPersonWithName("Данила", "Федоров")
                .goToFriends()
                .clickOnPersonWithName("Артём", "Галушко")
                .addToFriends();

        assertFalse(currentPage.isInvitedToFriends());

        refresh(); //Сброс
        new PersonPage().revokeInvite();
    }

    @After
    public void revokeInvite() {
        //api revoking
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

    @After
    public void restoreInvite() {
        //api inviting
    }

    @Test
    public void votingTest() {
        GroupPage currentPage = new GroupPage().openGroupPage();
        PostCard currentPost = PostCard.getPostWithText("Мы все умрём?");
        currentPost.clickOnOptionWithText("Да")
                .checkOptionWithTextClicked("Да")
                .clickOnOptionWithText("Нет")
                .verifyVote()
                .checkOptionWithTextClicked("Нет");

        currentPost.clickOnOptionWithText("Нет").verifyVote(); //Сброс
    }

    @After
    public void restoreVoting() {
        //api deselection
    }

    @Test
    public void postCommentingTest() {
        //todo рандомизация сообщения/поста
        final String MESSAGE = "QWERTY";
        GroupPage currentPage = new GroupPage().openGroupPage();
        PostCard currentPost = PostCard.getPostWithText("Мы все умрём?");

        DiscussionsPage currentDiscussion = currentPost.openComments();
        Message message = currentDiscussion.typeMessage(MESSAGE)
                .sendMessage()
                .getLastMessage();
        assertEquals(message.getText(), MESSAGE);

        message.Remove(); //Cброс
    }
}
