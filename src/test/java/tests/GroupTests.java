package tests;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;
import pages.MessageCard;


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
    private static final Logger logger = LoggerFactory.getLogger(GroupTests.class);

    @Test
    public void votingTest() {
        final String POST = "Мы все умрём?";
        final String FIRST_OPTION = "Да";
        final String SECOND_OPTION = "Нет";

        logger.info("Run voting test for {} post with {} and {} options", POST, FIRST_OPTION, SECOND_OPTION);

        GroupPage currentPage = new GroupPage().openGroupTopicsPage(GROUP_ID);
        PostCard currentPost = currentPage.getPostWithText(POST);
        currentPost.clickOnOptionWithText(FIRST_OPTION)
                .checkOptionWithTextClicked(FIRST_OPTION)
                .clickOnOptionWithText(SECOND_OPTION)
                .verifyVote()
                .checkOptionWithTextClicked(SECOND_OPTION);
        logger.info("Voting test successful");

        currentPost.clickOnOptionWithText(SECOND_OPTION).verifyVote(); // сброс
        logger.info("Test restored");
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

        logger.info("Run creating post with {} text and {} video", TEXT, title);

        GroupPage currentPage = new GroupPage().openGroupPage(GROUP_ID);
        currentPage.pressCreateTopic()
                .typeTopicText(TEXT)
                .addVideoWithTitle(title)
                .sharePost();

        PostCard post = currentPage.getPostWithText(TEXT);
        assertThat(post.getVideoTitle(), equalTo(title));
        logger.info("Video post test successful");

        post.getPostPage()      // сброс
                .deletePost();
        logger.info("Test restored");
    }

    @Test
    public void geoTopicTest() {
        final String TEXT = getRandomString();
        final String PLACE = "SPBPU";

        logger.info("Run creating post with {} text and {} geo position", TEXT, PLACE);

        GroupPage currentPage = new GroupPage().openGroupPage(GROUP_ID);
        currentPage.pressCreateTopic()
                .typeTopicText(TEXT)
                .addGeoWithPlace(PLACE)
                .sharePost();

        PostCard post = currentPage.getPostWithText(TEXT);
        assertTrue(post.hasPlaceMap());
        assertThat(post.getPostText(), equalTo(TEXT));
        logger.info("Geo post test successful");

        post.getPostPage()      // сброс
                .deletePost();
        logger.info("Test restored");
    }

    @Test
    public void sendMusicTopicTest() {
        final String MUSIC = "Enter Sandman";
        final String TEXT = getRandomString();

        logger.info("Run creating post with {} text and {} music", TEXT, MUSIC);

        GroupPage currentPage = new GroupPage().openGroupPage(GROUP_ID);
        currentPage.pressCreateTopic()
                .typeTopicText(TEXT)
                .addMusicWithName(MUSIC)
                .sharePost();

        PostCard post = currentPage.getPostWithText(TEXT);
        assertThat(post.getPostText(), equalTo(TEXT));
        assertThat(post.getMusicNameOfPost(), equalTo(MUSIC));
        logger.info("Music post test successful");

        post.getPostPage()      // сброс
                .deletePost();
        logger.info("Test restored");
    }

    @Test
    public void postCommentingTest() {
        final String POST = "стоимость техасской нефти";
        final String MESSAGE = getRandomString();

        logger.info("Run commenting {} post with {} message test", POST, MESSAGE);

        GroupPage currentPage = new GroupPage().openGroupTopicsPage(GROUP_ID);
        PostCard currentPost = currentPage.getPostWithText(POST);

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
        logger.info("Commenting test successful");

        message.Remove(); // сброс
        logger.info("Test restored");
    }

    @Test
    public void productCreatingTest() {
        final String PRODUCT_TITLE = getRandomString();
        final String PRODUCT_DESCRIPTION = getRandomString();
        final String PRODUCT_PRICE = getRandomNumber();

        logger.info("Run creating product test with {} title, {} price, {} description", PRODUCT_TITLE, PRODUCT_PRICE, PRODUCT_DESCRIPTION);

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
        logger.info("Creating product test successful");

        product.deleteProduct(); // сброс
        logger.info("Test restored");
    }

    @Test
    public void catalogCreatingTest() {
        final String CATALOG_NAME = getRandomString();

        logger.info("Run creating catalog test with {} name", CATALOG_NAME);

        GroupProductsPage currentPage = new GroupPage()
                .openGroupPage(GROUP_ID)
                .goToProducts();

        currentPage = currentPage.clickCreateCatalog()
                .typeName(CATALOG_NAME) // .sendCoverPhoto()
                .clickSave();

        CatalogPage catalog = currentPage.clickOnCatalogWithName(CATALOG_NAME);

        assertThat(catalog.getCatalogName(), equalTo(CATALOG_NAME));
        logger.info("Creating catalog test successful");

        catalog.deleteCatalog(); // сброс
        logger.info("Test restored");
    }

    @Test
    public void changingCatalogOfProductTest() {
        final String PRODUCT_TITLE = "Не пёсик";
        final String PRODUCT_CATALOG = "Пёсики";
        final String TARGET_CATALOG = "Котятки";

        logger.info("Run test changing {} product catalog from {} to {}", PRODUCT_TITLE, PRODUCT_CATALOG, TARGET_CATALOG);

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
        logger.info("Changing product catalog successful");

        product.editProduct()   // сброс
                .deleteCatalog()
                .typeCatalog(PRODUCT_CATALOG)
                .clickShare();
        logger.info("Test restored");
    }

    @After
    public void restoreCondition() {
        //api restoring
    }
}
