package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.element;
import static org.openqa.selenium.By.xpath;


public abstract class BaseTopic {

    private static final By NEW_TOPIC_WINDOW = xpath(".//div[text() = 'Create a new topic']");
    private static final By SHARE_BUTTON = xpath(".//div[contains(@data-moderate,'Send for moderation')]");

    protected BaseTopic selectNewTopicWindows(){
        element(NEW_TOPIC_WINDOW).click();
        return this;
    }

    protected boolean isOpendTopicWindow(){
        $(SHARE_BUTTON).waitUntil(appear, 5000, 400);
        return $(SHARE_BUTTON).isDisplayed();
    }

    protected BaseTopic shareTopic() throws Exception {
        if(isOpendTopicWindow()){
            $(SHARE_BUTTON).click();
        }else throw new Exception("Окно создания топика не появилось");
        return this;
    }

    protected BaseTopic findTopic(String nameTopic){
        PostCard currentPost = PostCard.getPostWithText(nameTopic);
        return this;
    }
}
