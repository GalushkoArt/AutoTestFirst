package pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.By.xpath;

public class ProductPage extends BasePage {
    private static final By PRODUCT_TITLE = By.xpath(".//div[@class='product-layer_n textWrap']");
    private static final By PRODUCT_DESCRIPTION = By.xpath(".//div[@class='product-layer_description_tx']");
    private static final By PRODUCT_PRICE = By.xpath(".//div[@class='media-price_cnt']");
    private static final By PRODUCT_CATALOG = By.xpath(".//div[@class='tico']/a");
    private static final By DELETE_PRODUCT_BUTTON = xpath(".//div[@id='hook_Block_ShortcutMenu']//ul//a[contains(@href, 'Remove')]");
    private static final By EDIT_PRODUCT_BUTTON = xpath(".//div[@id='hook_Block_ShortcutMenu']//ul//a[contains(@href, 'Edit')]");
    private static final By RESTORE_PRODUCT_BUTTON = xpath(".//a[contains(@class, 'delete-stub_cancel')]");
    private static final By CLOSE_LAYER = xpath(".//div[@class='media-layer_hld']//div[contains(@class,'layer_close_ico')]");
    private static final By PRODUCT_OPTIONS = xpath(".//div[@class='mlr_top_ac']");

    private static final Logger logger = LoggerFactory.getLogger(ProductPage.class);

    public String getTitle() {
        logger.info("Asked product title");
        waitUntilShows(PRODUCT_TITLE);
        return getText(PRODUCT_TITLE);
    }
    public String getDescription() {
        logger.info("Asked product description");
        waitUntilShows(PRODUCT_DESCRIPTION);
        return getText(PRODUCT_DESCRIPTION);
    }

    public String getPrice() {
        logger.info("Asked product price");
        waitUntilShows(PRODUCT_PRICE);
        return getText(PRODUCT_PRICE).replaceAll("\\D", "");
    }

    public String getCatalog() {
        logger.info("Asked product catalog");
        waitUntilShows(PRODUCT_CATALOG);
        return getText(PRODUCT_CATALOG);
    }

    public ProductPage deleteProduct() {
        waitUntilShows(PRODUCT_OPTIONS);
        click(PRODUCT_OPTIONS);
        waitUntilShows(DELETE_PRODUCT_BUTTON);
        click(DELETE_PRODUCT_BUTTON);
        logger.info("Clicked delete product");
        waitUntilDisappear(DELETE_PRODUCT_BUTTON);
        click(CLOSE_LAYER);
        logger.info("Product deleted");
        return this;
    }

    public NewProductPage editProduct() {
        waitUntilShows(PRODUCT_OPTIONS);
        click(PRODUCT_OPTIONS);
        waitUntilShows(EDIT_PRODUCT_BUTTON);
        click(EDIT_PRODUCT_BUTTON);
        waitUntilDisappear(EDIT_PRODUCT_BUTTON);
        logger.info("Clicked edit product");
        return new NewProductPage();
    }
}
