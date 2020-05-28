package pages;

import org.openqa.selenium.By;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class GroupProductsPage extends BasePage {
    private static final String CATALOG_WITH_NAME = ".//div[@class='market-album soh-s']//a[@title=\"%s\"]";
    private static final String PRODUCT_WITH_TITLE = ".//div[@class='market-card_n']//a[text()=\"%s\"]";
    private static final By PLACE_BUTTON = xpath(".//a[contains(@class, 'button') and contains(@href, 'post')]");
    private static final By CREATE_CATALOG_BUTTON = xpath(".//a[contains(@class, 'button') and contains(@hrefattrs, 'AdvertSelectionCreate')]");

    public ProductPage openProductWithTitle(String title) {
        By product = xpath(format(PRODUCT_WITH_TITLE, title));
        waitUntilShows(product);
        scrollToElement(product);
        click(product);
        return new ProductPage();
    }

    public NewProductCard clickPlaceButton() {
        click(PLACE_BUTTON);
        return new NewProductCard();
    }

    public NewCatalogCard clickCreateCatalog() {
        click(CREATE_CATALOG_BUTTON);
        return new NewCatalogCard();
    }

    public CatalogPage clickOnCatalogWithName(String name) {
        click(xpath(format(CATALOG_WITH_NAME, name)));
        return new CatalogPage();
    }
}
