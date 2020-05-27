package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;
import static pages.NewTopicCard.CREATE_TOPIC_CARD;

public class GroupPage extends HelperBase {

    private static final String OPTION_WITH_TEXT = ".//*[contains(@class, 'u-menu_li') and .//*[contains(text(), \"%s\")]]";

    private static final By GROUP_NAME = xpath(".//*[@class = 'group-name_h']");
    private static final By GROUP_DESCRIPTION = xpath(".//*[contains(@class, 'group-info_desc')]");
    private static final By MORE_OPTIONS_BUTTON = xpath(".//*[contains(@class, 'expand-action-item')]");
    private static final By MORE_OPTIONS_CARD = xpath(".//*[contains(@class, 'dropdown_cnt')]");
    private static final By GROUP_DELETE_CONFIRM_CARD = xpath(".//*[@class = 'modal-new_cnt' and .//*[contains(text(), 'Удалить группу')]]");
    private static final By GROUP_DELETE_CONFIRM_BUTTON = xpath(".//input[contains(@data-l, 'confirm')]");

    public static final By CREATE_TOPIC_FIELD = xpath(".//*[@id='hook_Block_PostingForm']//*[@class='input_placeholder']");
    public static final By PRODUCTS_MENU = xpath(".//div[@class='mctc_navMenu __groups']//a[contains(@href, 'market')]");

    /**
     * opens test group page
     * @return new GroupPage
     */

    public GroupPage openGroupPage(String groupID) {
        Selenide.open("/group/" + groupID);
        return new GroupPage();
    }

    public NewTopicCard pressCreateTopic() {
        click(CREATE_TOPIC_FIELD);
        waitUntilShows(CREATE_TOPIC_CARD);
        return new NewTopicCard();
    }

    public GroupProductsPage goToProducts() {
        click(PRODUCTS_MENU);
        return new GroupProductsPage();
    }

    public GroupsPage deleteGroup() {
        click(MORE_OPTIONS_BUTTON);
        SelenideElement optionsMenu = $(MORE_OPTIONS_BUTTON).$(MORE_OPTIONS_CARD);
        //todo maybe make method in HelperBase for SelenideElement
        // because there are 2 different menus on page with same locator
        optionsMenu.waitUntil(appear, 5000, 400);
        SelenideElement option = optionsMenu.$(xpath(format(OPTION_WITH_TEXT, "Удалить")));
        option.click();
        waitUntilShows(GROUP_DELETE_CONFIRM_CARD);
        click(GROUP_DELETE_CONFIRM_BUTTON);
        return new GroupsPage();
    }

    public String getGroupName() {
        return $(GROUP_NAME).text();
    }

    public String getGroupDescription() {
        if (isElementDisplayed(GROUP_DESCRIPTION)) {
            return $(GROUP_DESCRIPTION).text();
        } else {
            return "";
        }
    }
}
