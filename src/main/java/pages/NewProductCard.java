package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.xpath;

public class NewProductCard extends BaseCard {
    private static final By PRODUCT_TITLE = xpath(".//input[@data-id='adHeader']");
    private static final By PRODUCT_PRICE = xpath(".//input[@data-id='adPrice']");
    private static final By PRODUCT_DESCRIPTION = xpath(".//div[@data-module='postingForm/mediaText']");
    private static final By PRODUCT_CATALOG = xpath(".//input[@class='tag_it']");
    private static final By PRODUCT_PICKUP_DELIVERY = xpath(".//span[.//input[@value='SELF']]");
    private static final By PRODUCT_MAIL_DELIVERY = xpath(".//span[.//input[@value='MAIL']]");
    private static final By DELETE_CATALOG_BUTTON = xpath(".//i[contains(@title,'deleteFriend')]");
    private static final By SHARE_BUTTON = xpath(".//div[@data-action='submit']");

    public NewProductCard() {
        super($x(".//div[@data-module='MediaTopicAdvertLayerBody']"));
    }

    public NewProductCard typeTitle(String title) {
        type(PRODUCT_TITLE, title);
        return this;
    }

    public NewProductCard typePrice(String price) {
        type(PRODUCT_PRICE, price);
        return this;
    }

    public NewProductCard typeDescription(String description) {
        type(PRODUCT_DESCRIPTION, description);
        return this;
    }

    public NewProductCard typeCatalog(String catalog) {
        type(PRODUCT_CATALOG, catalog);
        sendKey(PRODUCT_CATALOG, Keys.ENTER);
        waitUntilShows(DELETE_CATALOG_BUTTON);
        return this;
    }

    public NewProductCard deleteCatalog() {
        click(DELETE_CATALOG_BUTTON);
        return this;
    }

    public NewProductCard clickPickupDelivery() {
        click(PRODUCT_PICKUP_DELIVERY);
        return this;
    }

    public NewProductCard clickMailDelivery() {
        click(PRODUCT_MAIL_DELIVERY);
        return this;
    }

    public GroupProductsPage clickShare() {
        click(SHARE_BUTTON);
        return new GroupProductsPage();
    }
}
