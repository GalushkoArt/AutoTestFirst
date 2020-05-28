package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class PostHelper {
    private static final String POST_WITH_TEXT = ".//div[(@class='feed-w' or @class='groups_post-w __search-enabled') and .//*[contains(text(), \"%s\")]]";
    private static final By POST = xpath(".//div[@class='feed-w' or @class='groups_post-w __search-enabled']");

    private PostHelper(){}

    /**
     * scrolls to post with text returns new PostCard
     * @param text text for searching
     * @return new PostCard
     */

    static PostCard getPostWithText(String text) {
        SelenideElement postCard = $x(format(POST_WITH_TEXT, text));
        for (int i = 0; i < 10 && !Selenide.atBottom() && !postCard.exists(); ++i) {
            Selenide.executeJavaScript("window.scrollBy(0, window.innerHeight)");
            List<PostCard> cards = getPostsWithText(text);
            if (!cards.isEmpty()) {
                cards.get(0).scrollTo();
                return cards.get(0);
            }
            sleep(200);
        }
        return new PostCard(postCard.scrollTo());
    }

    static List<PostCard> getPosts() {
        ElementsCollection posts = $$(POST);
        return posts.stream()
                .map(PostCard::new)
                .collect(Collectors.toList());
    }

    static List<PostCard> getPostsWithText(String text) {
        return getPosts().stream()
                .filter(post -> post.getPostText().equals(text))
                .collect(Collectors.toList());
    }
}
