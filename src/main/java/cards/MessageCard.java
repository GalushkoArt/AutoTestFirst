package cards;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class MessageCard extends BaseCard {
    private static final By NAME = xpath(".//*[contains(@class, 'comments_author-name')]");
    private static final By TEXT = xpath(".//*[contains(@class, 'comment_text') and contains(@class, 'textWrap')]");
    private static final By REMOVE_BUTTON = xpath(".//*[contains(@class, 'comment_act')]//*[contains(@class, 'close')]");
    private static final By REMOVE_CONFIRM_BUTTON = xpath(".//input[@type = 'button']");

    public MessageCard(SelenideElement element) {
        super(element);
    }

    public String getName() {
        return getText(NAME);
    }

    public String getText() {
        return getText(TEXT);
    }

    public void Remove() {
        hover();
        click(REMOVE_BUTTON);
        confirm(REMOVE_CONFIRM_BUTTON);
    }
}
