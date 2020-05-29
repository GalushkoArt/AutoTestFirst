package pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.By.xpath;

public class PostPage extends BasePage {
    private static final By DELETE_POST_BUTTON = xpath(".//div[@id='hook_Block_ShortcutMenu']//ul//a[contains(@href, 'Remove')]");
    private static final By RESTORE_POST_BUTTON = xpath(".//a[contains(@class, 'delete-stub_cancel')]");
    private static final By CLOSE_LAYER = xpath(".//div[@class='media-layer_hld']//div[contains(@class,'layer_close_ico')]");
    private static final By POST_OPTIONS = xpath(".//div[@class='mlr_top_ac']");

    private static final Logger logger = LoggerFactory.getLogger(PostPage.class);

    public BasePage deletePost() {
        click(POST_OPTIONS);
        waitUntilShows(DELETE_POST_BUTTON);
        click(DELETE_POST_BUTTON);
        logger.info("Clicked delete post");
        waitUntilDisappear(DELETE_POST_BUTTON);
        click(CLOSE_LAYER);
        logger.info("Post deleted");
        return this;
    }
}
