package pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;
import static pages.NewGroupCard.CREATE_GROUP_CARD;

public class GroupsPage extends HelperBase {
    private static final By CREATE_GROUP_BUTTON = xpath(".//*[@class = 'create-group']");

    public static GroupsPage openGroupsPage() {
        Selenide.open("/groups");
        return new GroupsPage();
    }

    public NewGroupCard pressCreateGroup() {
        click(CREATE_GROUP_BUTTON);
        waitUntilShows(CREATE_GROUP_CARD);
        return new NewGroupCard();
    }


}
