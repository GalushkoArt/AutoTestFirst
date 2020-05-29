package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class PostCard extends BaseCard {
    private static final String POLL_OPTION_WITH_TEXT = ".//li[.//*[contains(text(), \"%s\")]]";
    private static final String VIDEO_WITH_TITLE = ".//a[@title=\"%s\" and contains(@class, 'video')]";
    private static final By VIDEO_TITLE = xpath(".//a[@class='video-card_n ellip']");
    private static final By POST_TEXT = xpath(".//div[@link-class='media-text_lnk']");
    private static final By POST_PLACE_MAP = xpath(".//div[@class='media-map']");
    private static final By POST_MUSIC = xpath(".//div[@class='media-block media-music']");
    private static final By MUSIC_TITLE = xpath(".//a[contains(@class,'track-with-cover_name')]");
    private static final By YES_BUTTON = xpath(".//span[contains(@class, 'button') and contains(@class, 'yes')]");
    private static final By COMMENTS_BUTTON = xpath(".//*[@data-module = 'CommentWidgets']");
    private static final By CLICKED_SELECTION = xpath(".//label[contains(@class, '__checked')]");

    private static final Logger logger = LoggerFactory.getLogger(PostCard.class);

    protected PostCard(SelenideElement element) {
        super(element);
    }

    /**
     * chooses option with text in post
     * @param text text for searching
     * @return current PostCard
     */

    public PostCard clickOnOptionWithText(String text) {
        click(xpath(format(POLL_OPTION_WITH_TEXT, text)));
        logger.info("Clicked option with {} text", text);
        return this;
    }

    /**
     * asserts that option with text is labeled as selected
     * @param text text for searching
     * @return current PostCard
     */

    public PostCard checkOptionWithTextClicked(String text) {
        verify(xpath(format(POLL_OPTION_WITH_TEXT, text)), CLICKED_SELECTION);
        logger.info("Option {} is chosen", text);
        return this;
    }

    /**
     * verifies chosen option
     * @return current PostCard
     */

    public PostCard verifyVote() {
        click(YES_BUTTON);
        logger.info("Vote verified");
        return this;
    }

    public boolean hasVideoWithTitle(String title) {
        return isExists(xpath(format(VIDEO_WITH_TITLE, title)));
    }

    public boolean hasPlaceMap() {
        logger.info("Asked if post has map");
        return isExists(POST_PLACE_MAP);
    }

    public String getVideoTitle() {
        logger.info("Asked post video title");
        return getText(VIDEO_TITLE);
    }

    public String getPostText() {
        return getText(POST_TEXT);
    }

    public String getMusicNameOfPost() {
        logger.info("Asked post music title");
        return getText(MUSIC_TITLE);
    }

    public PostPage getPostPage() {
        click(POST_TEXT);
        logger.info("Went to post page");
        return new PostPage();
    }

    public DiscussionsPage openComments() {
        click(COMMENTS_BUTTON);
        logger.info("Went to discussion page of the post");
        return new DiscussionsPage();
    }
}
