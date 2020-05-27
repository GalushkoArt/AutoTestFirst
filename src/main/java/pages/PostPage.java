package pages;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class PostPage extends HelperBase {
    private static final By DELETE_POST_BUTTON = xpath(".//div[@id='hook_Block_ShortcutMenu']//ul//a[contains(@href, 'Remove')]");
    private static final By RESTORE_POST_BUTTON = xpath(".//a[contains(@class, 'delete-stub_cancel')]");
    private static final By CLOSE_LAYER = xpath(".//div[@class='media-layer_hld']//div[contains(@class,'layer_close_ico')]");
    private static final By POST_OPTIONS = xpath(".//div[@class='mlr_top_ac']");


    public HelperBase deletePost() {
        click(POST_OPTIONS);
        waitUntilShows(DELETE_POST_BUTTON);
        click(DELETE_POST_BUTTON);
        waitUntilDisappear(DELETE_POST_BUTTON);
        click(CLOSE_LAYER);
        return this;
    }
}
