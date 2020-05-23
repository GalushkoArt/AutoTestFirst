package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class PostCard extends BaseCard{
    private static final String POST_WITH_TEXT = ".//div[@class='feed-w' and .//*[contains(text(), \"%s\")]]";
    private static final String ALT_POST_WITH_TEXT = ".//div[@class='portlet_b' and .//*[contains(text(), \"%s\")]]";
    private static final String POLL_OPTION_WITH_TEXT = ".//li[.//*[contains(text(), \"%s\")]]";
    private static final String VIDEO_WITH_TITLE = ".//a[@title=\"%s\" and contains(@class, 'video')]";
    private static final By POST_TEXT = xpath(".//a[@class='media-text_a']");
    private static final By YES_BUTTON = xpath(".//span[contains(@class, 'button') and contains(@class, 'yes')]");
    private static final By COMMENTS_BUTTON = xpath(".//*[@data-module = 'CommentWidgets']");
    public static final By CLICKED_SELECTION = xpath(".//label[contains(@class, '__checked')]");

    protected PostCard(SelenideElement element) {
        super(element);
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
        click(xpath(format(POLL_OPTION_WITH_TEXT, text)));
        return this;
    }

    /**
     * asserts that option with text is labeled as selected
     * @param text text for searching
     * @return current PostCard
     */

    public PostCard checkOptionWithTextClicked(String text) {
        verify(xpath(format(POLL_OPTION_WITH_TEXT, text)), CLICKED_SELECTION);
        return this;
    }

    /**
     * verifies chosen option
     * @return current PostCard
     */

    public PostCard verifyVote() {
        click(YES_BUTTON);
        return this;
    }

    public boolean hasVideoWithTitle(String title) {
        return isExists(xpath(format(VIDEO_WITH_TITLE, title)));
    }

    public PostPage getPostPage() {
        click(POST_TEXT);
        return new PostPage();
    }

    public DiscussionsPage openComments() {
        click(COMMENTS_BUTTON);
        return new DiscussionsPage();
    }
}
