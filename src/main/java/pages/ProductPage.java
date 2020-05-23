package pages;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class ProductPage extends HelperBase {
    private static final By PRODUCT_TITLE = By.xpath(".//div[@class='product-layer_n textWrap']");
    private static final By PRODUCT_DESCRIPTION = By.xpath(".//div[@class='product-layer_description_tx']");
    private static final By PRODUCT_PRICE = By.xpath(".//div[@class='media-price_cnt']");
    private static final By PRODUCT_CATALOG = By.xpath(".//div[@class='tico']/a");
    private static final By DELETE_PRODUCT_BUTTON = xpath(".//div[@id='hook_Block_ShortcutMenu']//ul//a[contains(@href, 'Remove')]");
    private static final By EDIT_PRODUCT_BUTTON = xpath(".//div[@id='hook_Block_ShortcutMenu']//ul//a[contains(@href, 'Edit')]");
    private static final By RESTORE_PRODUCT_BUTTON = xpath(".//a[contains(@class, 'delete-stub_cancel')]");
    private static final By CLOSE_LAYER = xpath(".//div[@class='media-layer_hld']//div[contains(@class,'layer_close_ico')]");
    private static final By PRODUCT_OPTIONS = xpath(".//div[@class='mlr_top_ac']");

    public String getTitle() {
        waitUntilShows(PRODUCT_TITLE);
        return getText(PRODUCT_TITLE);
    }
    public String getDescription() {
        waitUntilShows(PRODUCT_DESCRIPTION);
        return getText(PRODUCT_DESCRIPTION);
    }

    public String getPrice() {
        waitUntilShows(PRODUCT_PRICE);
        return getText(PRODUCT_PRICE).replaceAll("\\D", "");
    }

    public String getCatalog() {
        waitUntilShows(PRODUCT_CATALOG);
        return getText(PRODUCT_CATALOG);
    }

    public ProductPage deleteProduct() {
        click(PRODUCT_OPTIONS);
        waitUntilShows(DELETE_PRODUCT_BUTTON);
        click(DELETE_PRODUCT_BUTTON);
        waitUntilDisappear(DELETE_PRODUCT_BUTTON);
        click(CLOSE_LAYER);
        return this;
    }

    public NewProductCard editProduct() {
        click(PRODUCT_OPTIONS);
        waitUntilShows(EDIT_PRODUCT_BUTTON);
        click(EDIT_PRODUCT_BUTTON);
        waitUntilDisappear(EDIT_PRODUCT_BUTTON);
        return new NewProductCard();
    }
}
