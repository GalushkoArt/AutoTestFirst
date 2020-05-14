package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class PostCard extends HelperBase {
    private static final String POST_WITH_TEXT = ".//div[@class='feed-w' and .//*[contains(text(), \"%s\")]]";
    private static final String POLL_OPTION_WITH_TEXT = ".//li[.//*[contains(text(), \"%s\")]]";
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
        }
        return new PostCard(postCard);
    }

    /**
     * chooses option with text in post
     * @param text text for searching
     * @return current PostCard
     */

    public PostCard clickOnOptionWithText(String text) {
        waitUntilShows(xpath(format(POLL_OPTION_WITH_TEXT, text)));
        element.$x(format(POLL_OPTION_WITH_TEXT, text)).click();
        return this;
    }

    /**
     * asserts that option with text is labeled as selected
     * @param text text for searching
     * @return current PostCard
     */

    public PostCard checkOptionWithTextClicked(String text) {
        waitUntilShows(CLICKED_SELECTION);
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

    public DiscussionsPage openComments() {
        element.$(COMMENTS_BUTTON).hover();
        element.$(COMMENTS_BUTTON).click();
        return new DiscussionsPage();
    }
}
