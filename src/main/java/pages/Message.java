package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static org.openqa.selenium.By.xpath;

public class Message extends HelperBase {
    private static final By NAME = xpath(".//*[contains(@class, 'comments_author-name')]");
    private static final By TEXT = xpath(".//*[contains(@class, 'comment_text') and contains(@class, 'textWrap')]");
    private static final By REMOVE_BUTTON = xpath(".//*[contains(@class, 'comment_act')]//*[contains(@class, 'close')]");
    private static final By REMOVE_CONFIRM_BUTTON = xpath(".//input[@type = 'button']");
    private SelenideElement element;

    Message(SelenideElement element) {
        this.element = element;
    }

    public String getName() {
        return element.$(NAME).getText();
    }

    public String getText() {
        return element.$(TEXT).getText();
    }

    public void Remove() {
        element.hover();
        waitUntilShows(REMOVE_BUTTON);
        click(REMOVE_BUTTON);
        click(REMOVE_CONFIRM_BUTTON);
    }
}
