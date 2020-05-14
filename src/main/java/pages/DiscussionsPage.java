package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.openqa.selenium.By.xpath;

public class DiscussionsPage extends HelperBase {
    private static final By TEXT_FIELD = xpath(".//*[@contenteditable = 'true']");
    private static final By SEND_BUTTTON = xpath(".//*[@uid = 'sendComment']");
    private static final By CLOSE_BUTTON = xpath(".//*[@uid = 'closeDisc']");
    private static final By MESSAGE = xpath(".//*[contains(@class, 'comment') and contains(@class, 'avatar')]");

    public DiscussionsPage typeMessage(String msg) {
        waitUntilShows(TEXT_FIELD);
        type(TEXT_FIELD, msg);
        return this;
    }

    public DiscussionsPage sendMessage() {
        click(SEND_BUTTTON);
        return this;
    }

    public List<Message> getMessages() {
        ElementsCollection messages = $$(MESSAGE);
        return messages.stream()
                .map(Message::new)
                .collect(Collectors.toList());
    }

    public void close() {
        click(CLOSE_BUTTON);
    }
}
