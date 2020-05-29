package pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class NewTopicPage extends BasePage {

    private static final By TEXT_FIELD = xpath(".//*[@data-module=\"postingForm/mediaText\"]");
    private static final By ADD_VIDEO_BUTTON = xpath(".//*[@data-id=\"Group_add_video\"]");
    private static final By ADD_GEO_BUTTON = xpath(".//div[contains(@id, 'PlaceMarker')]");
    private static final By ADD_MUSIC_BUTTON = xpath(".//div[contains(@data-module,'MusicAddButton')]");
    private static final By RESTORE_DRAFT_WINDOW = xpath(".//*[contains(@id, \"QuitConfirmationForm\")]");
    private static final By SHARE_BUTTON = xpath(".//*[@data-action=\"submit\"]");
    private static final By CLOSE_VIDEO = xpath(".//*[@class='ic media-layer_close_ico']");
    private static final By CANCEL_RESTORE = xpath(".//*[@data-js-cancel='1']");
    public static final By CREATE_TOPIC_CARD = cssSelector("div#hook_Block_MediaTopicLayerBody");

    private static final Logger logger = LoggerFactory.getLogger(NewTopicPage.class);

    public NewTopicPage() {
        $(CREATE_TOPIC_CARD).scrollTo();
        if ($(RESTORE_DRAFT_WINDOW).isDisplayed()) {
            logger.info("Restore draft window opened");
            $(CANCEL_RESTORE).waitUntil(visible, 5000, 400);
            $(CANCEL_RESTORE).click();
        }
    }

    public NewTopicPage typeTopicText(String text) {
        type(TEXT_FIELD, text);
        logger.info("Typed {} topic text", text);
        return this;
    }

    public NewTopicPage addVideoWithTitle(String title) {
        click(ADD_VIDEO_BUTTON);
        new VideoSectionPage().addVideoWithTitle(title);
        logger.info("Added video with {} title", title);
        return this;
    }

    public NewTopicPage addGeoWithPlace(String place) {
        click(ADD_GEO_BUTTON);
        new GeoTopicPage(place)
                .chosePlace()
                .confirmPlace();
        logger.info("Added geo data with {} place", place);
        return this;
    }

    public NewTopicPage addMusicWithName(String name) {
        click(ADD_MUSIC_BUTTON);
        new MusicSectionPage(name).choseMusicWithName();
        logger.info("Added music with {} title", name);
        return this;
    }

    public GroupPage sharePost() {
        click(SHARE_BUTTON);
        logger.info("Clicked share post");
        return new GroupPage();
    }

}
