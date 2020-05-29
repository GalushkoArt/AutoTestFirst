package pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.By.xpath;
import static pages.NewTopicPage.CREATE_TOPIC_CARD;

public class GroupPage extends BasePage {

    private static final String OPTION_WITH_TEXT = ".//*[contains(@class, 'u-menu_li') and .//*[contains(text(), \"%s\")]]";

    private static final By REMOVE_OPTION = xpath(".//*[contains(@class, 'u-menu_li')]//*[contains(@hrefattrs,'RemoveAltGroup')]");
    private static final By GROUP_NAME = xpath(".//*[@class = 'group-name_h']");
    private static final By GROUP_DESCRIPTION = xpath(".//*[contains(@class, 'group-info_desc')]");
    private static final By MORE_OPTIONS_BUTTON = xpath(".//*[contains(@class, 'expand-action-item')]");
    private static final By MORE_OPTIONS_CARD = xpath(".//*[contains(@class, 'expand-action-item')]//*[contains(@class, 'dropdown_cnt')]");
    private static final By GROUP_DELETE_CONFIRM_CARD = xpath(".//*[@class = 'modal-new_cnt' and .//*[contains(@id,'RemoveAltGroupForm')]]");
    private static final By GROUP_DELETE_CONFIRM_BUTTON = xpath(".//input[contains(@data-l, 'confirm')]");

    public static final By CREATE_TOPIC_FIELD = xpath(".//*[@id='hook_Block_PostingForm']//*[@class='input_placeholder']");
    public static final By PRODUCTS_MENU = xpath(".//div[@class='mctc_navMenu __groups']//a[contains(@href, 'market')]");
    
    private static final Logger logger = LoggerFactory.getLogger(GroupPage.class);

    /**
     * opens test group page
     * @return new GroupPage
     */

    public GroupPage openGroupPage(String groupID) {
        Selenide.open("/group/" + groupID);
        logger.info("Opened {} group", groupID);
        return new GroupPage();
    }

    public GroupPage openGroupTopicsPage(String groupID) {
        Selenide.open("/group/" + groupID+"/topics");
        logger.info("Opened topics {} group", groupID);
        return new GroupPage();
    }

    public PostCard getPostWithText(String text) {
        PostCard post = PostHelper.getPostWithText(text);
        logger.info("Found post with {} text", text);
        return post;
    }

    public NewTopicPage pressCreateTopic() {
        click(CREATE_TOPIC_FIELD);
        waitUntilShows(CREATE_TOPIC_CARD);
        logger.info("Create new topic clicked");
        return new NewTopicPage();
    }

    public GroupProductsPage goToProducts() {
        click(PRODUCTS_MENU);
        logger.info("Went to products menu");
        return new GroupProductsPage();
    }

    public GroupsPage deleteGroup() {
        hover(MORE_OPTIONS_BUTTON);
        click(MORE_OPTIONS_BUTTON);
        logger.info("Opened options");
        waitUntilShows(MORE_OPTIONS_CARD);
        click(REMOVE_OPTION);
        logger.info("Clicked remove option");
        waitUntilShows(GROUP_DELETE_CONFIRM_CARD);
        click(GROUP_DELETE_CONFIRM_BUTTON);
        logger.info("Confirmed remove group");
        return new GroupsPage();
    }

    public String getGroupName() {
        logger.info("Asked group name");
        return getText(GROUP_NAME);
    }

    public String getGroupDescription() {
        logger.info("Asked group description");
        if (isElementDisplayed(GROUP_DESCRIPTION)) {
            return getText(GROUP_DESCRIPTION);
        } else {
            return "";
        }
    }
}
