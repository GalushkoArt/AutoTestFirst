package pages;

import org.openqa.selenium.By;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class VideoSectionPage extends BasePage {
    private static final String VIDEO_WITH_TITLE = ".//div[@class='ugrid_i' and .//a[@title=\"%s\"]]//a[@class='video-card_lk']";

    public NewTopicCard addVideoWithTitle(String title) {
        By videoWithTitle = xpath(format(VIDEO_WITH_TITLE, title));
        waitUntilShows(videoWithTitle);
        hover(videoWithTitle);
        click(videoWithTitle);
        waitUntilDisappear(videoWithTitle);
        return new NewTopicCard();
    }
}
