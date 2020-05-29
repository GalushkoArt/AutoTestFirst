package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.xpath;

public class DiscussionsPage extends BasePage {
    private static final By TEXT_FIELD = xpath(".//*[@contenteditable = 'true']");
    private static final By SEND_BUTTTON = xpath(".//*[@uid = 'sendComment']");
    private static final By CLOSE_BUTTON = xpath(".//*[@uid = 'closeDisc']");
    private static final By MESSAGE = xpath(".//*[contains(@class, 'comment') and contains(@class, 'avatar')]");

    private static final Logger logger = LoggerFactory.getLogger(DiscussionsPage.class);

    public DiscussionsPage typeMessage(String msg) {
        waitUntilShows(TEXT_FIELD);
        type(TEXT_FIELD, msg);
        logger.info("Typed {} message", msg);
        return this;
    }

    public DiscussionsPage sendMessage() {
        click(SEND_BUTTTON);
        logger.info("Clicked send button");
        return this;
    }

    public List<MessageCard> getMessages() {
        ElementsCollection messages = $$(MESSAGE);
        return messages.stream()
                .map(MessageCard::new)
                .collect(Collectors.toList());
    }

    public void close() {
        logger.info("Clicked close button");
        click(CLOSE_BUTTON);
    }
}
