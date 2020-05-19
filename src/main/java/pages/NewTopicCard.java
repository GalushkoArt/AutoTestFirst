package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class NewTopicCard {

    private static final String VIDEO_WITH_TITLE = ".//div[@class='ugrid_i' and .//a[@title=\"%s\"]]//a[@class='video-card_lk']";
    private static final By TEXT_FIELD = xpath(".//*[@data-module=\"postingForm/mediaText\"]");
    private static final By ADD_VIDEO_BUTTON = xpath(".//*[@data-id=\"Group_add_video\"]");
    private static final By RESTORE_DRAFT_WINDOW = xpath(".//*[contains(@id, \"QuitConfirmationForm\")]");
    private static final By SHARE_BUTTON = xpath(".//*[@data-action=\"submit\"]");
    private static final By CLOSE_VIDEO = xpath(".//*[@class='ic media-layer_close_ico']");
    public static final By CREATE_TOPIC_CARD = cssSelector("div#hook_Block_MediaTopicLayerBody");
    private SelenideElement element;

    public NewTopicCard() {
        element = $(CREATE_TOPIC_CARD).scrollTo();
        if ($(RESTORE_DRAFT_WINDOW).isDisplayed()) {
            $x(".//*[@data-js-cancel='1']").click();
        }
    }

    public NewTopicCard typeTopicText(String text) {
        element.$(TEXT_FIELD).sendKeys(text);
        return this;
    }

    public NewTopicCard addVideoWithTitle(String title) {
        element.$(ADD_VIDEO_BUTTON).click();
        $x(format(VIDEO_WITH_TITLE, title)).waitUntil(appear, 5000, 400);
        $x(format(VIDEO_WITH_TITLE, title)).click();
        return this;
    }

    public GroupPage sharePost() {
        element.$(SHARE_BUTTON).waitUntil(appear, 5000, 400);
        element.$(SHARE_BUTTON).click();
        return new GroupPage();
    }

}
