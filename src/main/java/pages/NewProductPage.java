package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.By.xpath;

public class NewProductPage extends BasePage {
    private static final By PRODUCT_TITLE = xpath(".//input[@data-id='adHeader']");
    private static final By PRODUCT_PRICE = xpath(".//input[@data-id='adPrice']");
    private static final By PRODUCT_DESCRIPTION = xpath(".//div[@data-module='postingForm/mediaText']");
    private static final By PRODUCT_CATALOG = xpath(".//input[@class='tag_it']");
    private static final By PRODUCT_PICKUP_DELIVERY = xpath(".//span[.//input[@value='SELF']]");
    private static final By PRODUCT_MAIL_DELIVERY = xpath(".//span[.//input[@value='MAIL']]");
    private static final By DELETE_CATALOG_BUTTON = xpath(".//i[contains(@title,'deleteFriend')]");
    private static final By SHARE_BUTTON = xpath(".//div[@data-action='submit']");

    private static final Logger logger = LoggerFactory.getLogger(NewProductPage.class);

    public NewProductPage typeTitle(String title) {
        type(PRODUCT_TITLE, title);
        logger.info("Typed {} title", title);
        return this;
    }

    public NewProductPage typePrice(String price) {
        type(PRODUCT_PRICE, price);
        logger.info("Typed {} price", price);
        return this;
    }

    public NewProductPage typeDescription(String description) {
        type(PRODUCT_DESCRIPTION, description);
        logger.info("Typed {} description", description);
        return this;
    }

    public NewProductPage typeCatalog(String catalog) {
        type(PRODUCT_CATALOG, catalog);
        sendKey(PRODUCT_CATALOG, Keys.ENTER);
        waitUntilShows(DELETE_CATALOG_BUTTON);
        logger.info("Typed {} catalog", catalog);
        return this;
    }

    public NewProductPage deleteCatalog() {
        click(DELETE_CATALOG_BUTTON);
        logger.info("Clicked delete catalog");
        return this;
    }

    public NewProductPage clickPickupDelivery() {
        click(PRODUCT_PICKUP_DELIVERY);
        logger.info("Chose pick up delivery");
        return this;
    }

    public NewProductPage clickMailDelivery() {
        click(PRODUCT_MAIL_DELIVERY);
        logger.info("Chose mail delivery");
        return this;
    }

    public GroupProductsPage clickShare() {
        click(SHARE_BUTTON);
        logger.info("Shared product");
        return new GroupProductsPage();
    }
}
