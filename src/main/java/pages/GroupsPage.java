package pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.By.xpath;
import static pages.NewGroupPage.CREATE_GROUP_CARD;

public class GroupsPage extends BasePage {
    private static final By CREATE_GROUP_BUTTON = xpath(".//*[@class = 'create-group']");

    private static final Logger logger = LoggerFactory.getLogger(GroupsPage.class);

    public static GroupsPage openGroupsPage() {
        Selenide.open("/groups");
        logger.info("Went to users groups");
        return new GroupsPage();
    }

    public NewGroupPage pressCreateGroup() {
        click(CREATE_GROUP_BUTTON);
        waitUntilShows(CREATE_GROUP_CARD);
        logger.info("Clicked create new group");
        return new NewGroupPage();
    }


}
