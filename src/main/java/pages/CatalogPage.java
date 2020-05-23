package pages;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class CatalogPage extends HelperBase {
    private static final By DELETE_CATALOG_BUTTON = xpath(".//a[contains(@class, 'button') and contains(@hrefattrs, 'AltGroupAdvertSelectionRemove')]");
    private static final By DELETE_BUTTON = xpath(".//input[@type='submit']");
    private static final By CATALOG_NAME = xpath(".//div[@class='market-panel_h ellip']");

    public GroupProductsPage deleteCatalog() {
        click(DELETE_CATALOG_BUTTON);
        waitUntilShows(DELETE_BUTTON);
        click(DELETE_BUTTON);
        return new GroupProductsPage();
    }

    public String getCatalogName() {
        return getText(CATALOG_NAME);
    }
}
