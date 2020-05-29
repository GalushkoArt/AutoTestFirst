package pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class GroupProductsPage extends BasePage {
    private static final String CATALOG_WITH_NAME = ".//div[@class='market-album soh-s']//a[@title=\"%s\"]";
    private static final String PRODUCT_WITH_TITLE = ".//div[@class='market-card_n']//a[text()=\"%s\"]";
    private static final By PLACE_BUTTON = xpath(".//a[contains(@class, 'button') and contains(@href, 'post')]");
    private static final By CREATE_CATALOG_BUTTON = xpath(".//a[contains(@class, 'button') and contains(@hrefattrs, 'AdvertSelectionCreate')]");

    private static final Logger logger = LoggerFactory.getLogger(GroupProductsPage.class);

    public ProductPage openProductWithTitle(String title) {
        By product = xpath(format(PRODUCT_WITH_TITLE, title));
        waitUntilShows(product);
        scrollToElement(product);
        click(product);
        logger.info("Went to {} product page", title);
        return new ProductPage();
    }

    public NewProductPage clickPlaceButton() {
        click(PLACE_BUTTON);
        logger.info("Clicked place product");
        return new NewProductPage();
    }

    public NewCatalogPage clickCreateCatalog() {
        click(CREATE_CATALOG_BUTTON);
        logger.info("Clicked add new catalog");
        return new NewCatalogPage();
    }

    public CatalogPage clickOnCatalogWithName(String name) {
        click(xpath(format(CATALOG_WITH_NAME, name)));
        logger.info("Went to {} catalog", name);
        return new CatalogPage();
    }
}
