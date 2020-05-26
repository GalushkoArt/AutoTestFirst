package tests;

import org.junit.After;
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

import static org.junit.jupiter.api.Assertions.*;


public class OkTest extends TestBase {
    private static final String groupID = "57637278384344";

    @Test
    public void votingTest() {
        GroupPage currentPage = new GroupPage().openGroupPage(groupID);
        PostCard currentPost = PostCard.getPostWithText("Мы все умрём?");
        currentPost.clickOnOptionWithText("Да")
                .checkOptionWithTextClicked("Да")
                .clickOnOptionWithText("Нет")
                .verifyVote()
                .checkOptionWithTextClicked("Нет");

        currentPost.clickOnOptionWithText("Нет").verifyVote(); // сброс
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

        GroupPage currentPage = new GroupPage().openGroupPage(groupID);
        currentPage.pressCreateTopic()
                .typeTopicText(TEXT)
                .addVideoWithTitle(title)
                .sharePost();

        PostCard post = PostCard.getPostWithText(TEXT);
        assertTrue(post.hasVideoWithTitle(title));

        post.getPostPage()      // сброс
                .deletePost();
    }

    @Test
    public void postCommentingTest() {
        final String MESSAGE = getRandomString();
        GroupPage currentPage = new GroupPage().openGroupPage(groupID);
        PostCard currentPost = PostCard.getPostWithText("стоимость техасской нефти");

        DiscussionsPage currentDiscussion = currentPost.openComments();
        List<MessageCard> messages = currentDiscussion.typeMessage(MESSAGE)
                .sendMessage()
                .getMessages();

        messages = messages.stream()
                .filter((msg) -> msg.getName().equals(bot.getBotName()))
                .collect(Collectors.toList());

        assertFalse(messages.isEmpty());

        MessageCard message = messages.get(messages.size() - 1);
        assertEquals(message.getText(), MESSAGE);

        message.Remove(); // сброс
    }

    @Test
    public void productCreatingTest() {
        final String PRODUCT_TITLE = getRandomString();
        final String PRODUCT_DESCRIPTION = getRandomString();
        final String PRODUCT_PRICE = getRandomNumber();

        GroupProductsPage currentPage = new GroupPage()
                .openGroupPage(groupID)
                .goToProducts();

        currentPage = currentPage
                .clickPlaceButton()
                .typeTitle(PRODUCT_TITLE)
                .typeDescription(PRODUCT_DESCRIPTION)
                .typePrice(PRODUCT_PRICE)
                .clickMailDelivery()
                .clickShare();

        ProductPage product = currentPage.openProductWithTitle(PRODUCT_TITLE);

        assertEquals(PRODUCT_TITLE, product.getTitle());
        assertEquals(PRODUCT_DESCRIPTION, product.getDescription());
        assertEquals(PRODUCT_PRICE, product.getPrice());

        product.deleteProduct(); // сброс
    }

    @Test
    public void catalogCreatingTest() {
        final String CATALOG_NAME = getRandomString();

        GroupProductsPage currentPage = new GroupPage()
                .openGroupPage(groupID)
                .goToProducts();

        currentPage = currentPage.clickCreateCatalog()
                .typeName(CATALOG_NAME) // .sendCoverPhoto()
                .clickSave();

        CatalogPage catalog = currentPage.clickOnCatalogWithName(CATALOG_NAME);

        assertEquals(CATALOG_NAME, catalog.getCatalogName());

        catalog.deleteCatalog(); // сброс
    }

    @Test
    public void changingCatalogOfProductTest() {
        final String CATALOG_NAME = "Котятки";
        final String PRODUCT_TITLE = "Не пёсик";

        GroupProductsPage currentPage = new GroupPage()
                .openGroupPage(groupID)
                .goToProducts();

        currentPage.openProductWithTitle(PRODUCT_TITLE)
                .editProduct()
                .deleteCatalog()
                .typeCatalog(CATALOG_NAME)
                .clickShare();

        ProductPage product = new ProductPage();

        assertEquals(CATALOG_NAME, product.getCatalog());

        product.editProduct()   // сброс
                .deleteCatalog()
                .typeCatalog("Пёсики")
                .clickShare();
    }

    @Test
    public void creatingGroupTest() {
        final NewGroupCard.groupTypes groupType = NewGroupCard.groupTypes.PUBLIC;
        final String groupName = "Название тестовой группы";
        final String groupDescription = "Это описание тестовой группы";

        NewGroupCard card = GroupsPage.openGroupsPage().pressCreateGroup();
        GroupPage groupPage = card.chooseGroupType(groupType)
                .typeGroupName(groupName)
                .typeGroupDescription(groupDescription)
                .create();

        assertEquals(groupName, groupPage.getGroupName());
        assertEquals(groupDescription, groupPage.getGroupDescription());

        groupPage.deleteGroup(); //сброс
    }

    @After
    public void restoreCondition() {
        //api restoring
    }
}
