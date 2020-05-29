package pages;


import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.By.xpath;

public class NewCatalogPage extends BasePage {
    private static final By CATALOG_COVER = xpath(".//div[contains(@class, '__visible')]/span/input[@type='file']");
    private static final By SAVE_BUTTON = xpath(".//input[@type='submit']");
    private static final By CATALOG_NAME = xpath(".//input[@id='field_name']");

    private static final Logger logger = LoggerFactory.getLogger(NewCatalogPage.class);

    public NewCatalogPage typeName(String name) {
        type(CATALOG_NAME, name);
        logger.info("Typed {} catalog name", name);
        return this;
    }

    public NewCatalogPage sendCoverPhoto() {
        type(CATALOG_COVER, "src\\test\\java\\tests\\resources\\noise.jpg");
        logger.info("Sent catalog cover");
        return this;
    }

    public GroupProductsPage clickSave() {
        click(SAVE_BUTTON);
        logger.info("Clicked save button");
        return new GroupProductsPage();
    }
}
