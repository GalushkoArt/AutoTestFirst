package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class PostCard {
    private static final String POST_WITH_TEXT = ".//div[@class='feed-w' and .//*[contains(text(), \"%s\")]]";
    private static final String POLL_OPTION_WITH_TEXT = ".//li[.//*[contains(text(), \"%s\")]]";
    private static final String VIDEO_WITH_TITLE = ".//a[@title=\"%s\" and contains(@class, 'video')]";
    private static final By POST_OPTIONS = cssSelector("div#feed_menu");
    private static final By DELETE_POST_BUTTON = xpath(".//i[contains(@class, 'delete')]");
    private static final By YES_BUTTON = xpath(".//span[contains(@class, 'button') and contains(@class, 'yes')]");
    private static final By COMMENTS_BUTTON = xpath(".//*[@data-module = 'CommentWidgets']");
    public static final By CLICKED_SELECTION = xpath(".//label[contains(@class, '__checked')]");
    private SelenideElement element;

    protected PostCard(SelenideElement element) {
        this.element = element;
    }

    /**
     * scrolls to post with text returns new PostCard
     * @param text text for searching
     * @return new PostCard
     */

    public static PostCard getPostWithText(String text) {
        SelenideElement postCard = $x(format(POST_WITH_TEXT, text));
        for (int i = 0; i < 10 && !Selenide.atBottom() && !postCard.exists(); ++i) {
            Selenide.executeJavaScript("window.scrollBy(0, window.innerHeight)");
            Selenide.sleep(500);
        }
        return new PostCard(postCard);
    }

    /**
     * chooses option with text in post
     * @param text text for searching
     * @return current PostCard
     */

    public PostCard clickOnOptionWithText(String text) {
        element.$x(format(POLL_OPTION_WITH_TEXT, text)).waitUntil(appear, 5000, 400);
        element.$x(format(POLL_OPTION_WITH_TEXT, text)).click();
        return this;
    }

    /**
     * asserts that option with text is labeled as selected
     * @param text text for searching
     * @return current PostCard
     */

    public PostCard checkOptionWithTextClicked(String text) {
        element.$(CLICKED_SELECTION).waitUntil(appear, 5000, 400);
        element.$x(format(POLL_OPTION_WITH_TEXT, text))
                .$(CLICKED_SELECTION)
                .shouldBe(visible);
        return this;
    }

    /**
     * verifies chosen option
     * @return current PostCard
     */

    public PostCard verifyVote() {
        element.$(YES_BUTTON).click();
        return this;
    }

    public boolean hasVideoWithTitle(String title) {
        element.$x(format(VIDEO_WITH_TITLE, title)).waitUntil(appear, 5000, 400);
        return element.$x(format(VIDEO_WITH_TITLE, title)).exists();
    }

    //TODO fix deletePost method
    public PostCard deletePost() {
        element.$(POST_OPTIONS).click();
        element.$(DELETE_POST_BUTTON).click();
        return this;
    }

    public DiscussionsPage openComments() {
        element.$(COMMENTS_BUTTON).hover();
        element.$(COMMENTS_BUTTON).click();
        return new DiscussionsPage();
    }
}
