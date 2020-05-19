package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class PostCard {
    private static final String POST_WITH_TEXT = ".//div[@class='feed-w' and .//*[contains(text(), \"%s\")]]";
    private static final String ALT_POST_WITH_TEXT = ".//div[@class='portlet_b' and .//*[contains(text(), \"%s\")]]";
    private static final String POLL_OPTION_WITH_TEXT = ".//li[.//*[contains(text(), \"%s\")]]";
    private static final String VIDEO_WITH_TITLE = ".//a[@title=\"%s\" and contains(@class, 'video')]";
    private static final By DELETE_POST_BUTTON = xpath(".//div[@class='mlr_top_ac']//ul//a[contains(@href, 'Remove')]");
    private static final By CLOSE_LAYER = xpath(".//div[contains(@class,'layer_close']");
    private static final By POST_TEXT = xpath(".//a[@class='media-text_a']");
    private static final By POST_OPTIONS = xpath(".//div[@class='mlr_top_ac']");
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
        SelenideElement altPostCard = $x(format(ALT_POST_WITH_TEXT, text));
        for (int i = 0; i < 10 && !Selenide.atBottom() && !postCard.exists(); ++i) {
            Selenide.executeJavaScript("window.scrollBy(0, window.innerHeight)");
            sleep(200);  // todo попробовать заменить слип на ожидание
        }
        if (postCard.exists()) {
            postCard.scrollTo();
            return new PostCard(postCard);
        } else {
            altPostCard.scrollTo();
            return new PostCard(altPostCard);
        }
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

    public PostCard deletePost() {
        element.$(POST_TEXT).click();
        $(POST_OPTIONS).click();
        $(DELETE_POST_BUTTON).click();  //todo разобраться почему здесь падает
        $(CLOSE_LAYER).click();
        return this;
    }

    public DiscussionsPage openComments() {
        element.$(COMMENTS_BUTTON).hover();
        element.$(COMMENTS_BUTTON).click();
        return new DiscussionsPage();
    }
}
