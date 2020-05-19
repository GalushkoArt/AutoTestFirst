package pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static pages.NewTopicCard.CREATE_TOPIC_CARD;

public class GroupPage extends HelperBase {

    public static final By CREATE_TOPIC_FIELD = By.xpath(".//*[@id='hook_Block_PostingForm']//*[@class='input_placeholder']");

    /**
     * opens test group page
     * @return new GroupPage
     */

    public GroupPage openGroupPage() {
        Selenide.open("/group/57637278384344");
        return new GroupPage();
    }

    public NewTopicCard pressCreateTopic() {
        click(CREATE_TOPIC_FIELD);
        waitUntilShows(CREATE_TOPIC_CARD);
        return new NewTopicCard();
    }
}
