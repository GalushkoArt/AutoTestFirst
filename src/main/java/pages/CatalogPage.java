package pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.By.xpath;

public class CatalogPage extends BasePage {
    private static final By DELETE_CATALOG_BUTTON = xpath(".//a[contains(@class, 'button') and contains(@hrefattrs, 'AltGroupAdvertSelectionRemove')]");
    private static final By DELETE_BUTTON = xpath(".//input[@type='submit']");
    private static final By CATALOG_NAME = xpath(".//div[@class='market-panel_h ellip']");

    private static final Logger logger = LoggerFactory.getLogger(CatalogPage.class);

    public GroupProductsPage deleteCatalog() {
        click(DELETE_CATALOG_BUTTON);
        waitUntilShows(DELETE_BUTTON);
        click(DELETE_BUTTON);
        logger.info("Clicked and confirmed deleting catalog");
        return new GroupProductsPage();
    }

    public String getCatalogName() {
        logger.info("Asked catalog name");
        return getText(CATALOG_NAME);
    }
}
