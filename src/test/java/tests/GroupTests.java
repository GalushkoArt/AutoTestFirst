package tests;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.*;
import cards.PostCard;
import cards.MessageCard;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class GroupTests extends TestBase {
    private static final String GROUP_ID = "57637278384344";

    @Test
    public void votingTest() {
        final String POST = "Мы все умрём?";
        final String FIRST_OPTION = "Да";
        final String SECOND_OPTION = "Нет";

        GroupPage currentPage = new GroupPage().openGroupPage(GROUP_ID);
        PostCard currentPost = PostCard.getPostWithText(POST);
        currentPost.clickOnOptionWithText(FIRST_OPTION)
                .checkOptionWithTextClicked(FIRST_OPTION)
                .clickOnOptionWithText(SECOND_OPTION)
                .verifyVote()
                .checkOptionWithTextClicked(SECOND_OPTION);

        currentPost.clickOnOptionWithText(SECOND_OPTION).verifyVote(); // сброс
    }

    private static Stream<Arguments> randomTitleProvider() throws IOException {
        List<String> strings = Files.lines(Paths.get("src/test/java/tests/resources/titles.csv"))
                .collect(Collectors.toList());
        Collections.shuffle(strings);

        return Stream.of(Arguments.of(strings.get(0)));
    }

    @ParameterizedTest
    @MethodSource("randomTitleProvider")
    public void videoPostTest(String title) {
        final String TEXT = getRandomString();

        GroupPage currentPage = new GroupPage().openGroupPage(GROUP_ID);
        currentPage.pressCreateTopic()
                .typeTopicText(TEXT)
                .addVideoWithTitle(title)
                .sharePost();

        PostCard post = PostCard.getPostWithText(TEXT);
        assertThat(post.getVideoTitle(), equalTo(title));

        post.getPostPage()      // сброс
                .deletePost();
    }

    @Test
    public void geoTopicTest() {
        final String TEXT = getRandomString();
        final String PLACE = "SPBPU";

        GroupPage currentPage = new GroupPage().openGroupPage(GROUP_ID);
        currentPage.pressCreateTopic()
                .typeTopicText(TEXT)
                .addGeoWithPlace(PLACE)
                .sharePost();

        PostCard post = PostCard.getPostWithText(TEXT);
        assertTrue(post.hasPlaceMap());
        assertThat(post.getPostText(), equalTo(TEXT));

        post.getPostPage()      // сброс
                .deletePost();
    }

    @Test
    public void sendMusicTopicTest() {
        final String MUSIC = "Enter Sandman";
        final String TEXT = getRandomString();

        GroupPage currentPage = new GroupPage().openGroupPage(GROUP_ID);
        currentPage.pressCreateTopic()
                .typeTopicText(TEXT)
                .addMusicWithName(MUSIC)
                .sharePost();

        PostCard post = PostCard.getPostWithText(TEXT);
        assertThat(post.getPostText(), equalTo(TEXT));
        assertThat(post.getMusicNameOfPost(), equalTo(MUSIC));

        post.getPostPage()      // сброс
                .deletePost();
    }

    @Test
    public void postCommentingTest() {
        final String POST = "стоимость техасской нефти";
        final String MESSAGE = getRandomString();

        GroupPage currentPage = new GroupPage().openGroupPage(GROUP_ID);
        PostCard currentPost = PostCard.getPostWithText(POST);

        DiscussionsPage currentDiscussion = currentPost.openComments();
        List<MessageCard> messages = currentDiscussion.typeMessage(MESSAGE)
                .sendMessage()
                .getMessages();

        messages = messages.stream()
                .filter((msg) -> msg.getName().equals(bot.getBotName()))
                .collect(Collectors.toList());
        assertFalse(messages.isEmpty());

        MessageCard message = messages.get(messages.size() - 1);
        assertThat(message.getText(), equalTo(MESSAGE));

        message.Remove(); // сброс
    }

    @Test
    public void productCreatingTest() {
        final String PRODUCT_TITLE = getRandomString();
        final String PRODUCT_DESCRIPTION = getRandomString();
        final String PRODUCT_PRICE = getRandomNumber();

        GroupProductsPage currentPage = new GroupPage()
                .openGroupPage(GROUP_ID)
                .goToProducts();

        currentPage = currentPage
                .clickPlaceButton()
                .typeTitle(PRODUCT_TITLE)
                .typePrice(PRODUCT_PRICE)
                .typeDescription(PRODUCT_DESCRIPTION)
                .clickMailDelivery()
                .clickShare();

        ProductPage product = currentPage.openProductWithTitle(PRODUCT_TITLE);

        assertThat(product.getTitle(), equalTo(PRODUCT_TITLE));
        assertThat(product.getDescription(), equalTo(PRODUCT_DESCRIPTION));
        assertThat(parseInt(product.getPrice()), equalTo(parseInt(PRODUCT_PRICE)));

        product.deleteProduct(); // сброс
    }

    @Test
    public void catalogCreatingTest() {
        final String CATALOG_NAME = getRandomString();

        GroupProductsPage currentPage = new GroupPage()
                .openGroupPage(GROUP_ID)
                .goToProducts();

        currentPage = currentPage.clickCreateCatalog()
                .typeName(CATALOG_NAME) // .sendCoverPhoto()
                .clickSave();

        CatalogPage catalog = currentPage.clickOnCatalogWithName(CATALOG_NAME);

        assertThat(catalog.getCatalogName(), equalTo(CATALOG_NAME));

        catalog.deleteCatalog(); // сброс
    }

    @Test
    public void changingCatalogOfProductTest() {
        final String PRODUCT_TITLE = "Не пёсик";
        final String PRODUCT_CATALOG = "Пёсики";
        final String TARGET_CATALOG = "Котятки";

        GroupProductsPage currentPage = new GroupPage()
                .openGroupPage(GROUP_ID)
                .goToProducts();

        currentPage.openProductWithTitle(PRODUCT_TITLE)
                .editProduct()
                .deleteCatalog()
                .typeCatalog(TARGET_CATALOG)
                .clickShare();

        ProductPage product = new ProductPage();

        assertThat(product.getCatalog(), equalTo(TARGET_CATALOG));

        product.editProduct()   // сброс
                .deleteCatalog()
                .typeCatalog(PRODUCT_CATALOG)
                .clickShare();
    }

    @After
    public void restoreCondition() {
        //api restoring
    }
}
