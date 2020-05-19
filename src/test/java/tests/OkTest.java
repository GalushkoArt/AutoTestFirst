package tests;

import model.BotFactory;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.*;


public class OkTest extends TestBase {
    @BeforeAll
    public static void initTest() {
        baseUrl = "https://ok.ru/";
        bot = BotFactory.getOkBot();
        new PersonPage().openHomePage();
        new LoginPage().logIn(bot);
    }

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

    private static Stream<Arguments> randomStringProvider() throws IOException {
        List<String> strings = Files.lines(Paths.get("src/test/java/tests/resources/strings.csv"))
                .collect(Collectors.toList());
        Collections.shuffle(strings);

        return Stream.of(Arguments.of(strings.get(0)));
    }

    //@ParameterizedTest
    //@MethodSource("randomStringProvider")
    public void videoPostTest(String text) {
        final String TITLE = "Jva";

        GroupPage currentPage = new GroupPage().openGroupPage();
        currentPage = currentPage.pressCreateTopic()
                .typeTopicText(text)
                .addVideoWithTitle(TITLE)
                .sharePost();

        PostCard post = PostCard.getPostWithText(text);
        assertTrue(post.hasVideoWithTitle(TITLE));

        //todo пофиксить удаление поста post.deletePost();
    }

    @Test
    public void postCommentingTest() {
        //todo рандомизация сообщения/поста
        final String MESSAGE = "Верните мой 2008";
        GroupPage currentPage = new GroupPage().openGroupPage();
        PostCard currentPost = PostCard.getPostWithText("стоимость техасской нефти");

        DiscussionsPage currentDiscussion = currentPost.openComments();
        List<Message> messages = currentDiscussion.typeMessage(MESSAGE)
                .sendMessage()
                .getMessages();

        messages = messages.stream()
                .filter((msg) -> msg.getName().equals(bot.getBotName()))
                .collect(Collectors.toList());

        assertFalse(messages.isEmpty());

        Message message = messages.get(messages.size() - 1);
        assertEquals(message.getText(), MESSAGE);

        message.Remove(); //Cброс
    }

    @After
    public void restoreCondition() {
        //api restoring
    }
}
