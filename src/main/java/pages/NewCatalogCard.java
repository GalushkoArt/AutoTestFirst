package pages;


import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class NewCatalogCard extends BasePage {
    private static final By CATALOG_COVER = xpath(".//div[contains(@class, '__visible')]/span/input[@type='file']");
    private static final By SAVE_BUTTON = xpath(".//input[@type='submit']");
    private static final By CATALOG_NAME = xpath(".//input[@id='field_name']");

    public NewCatalogCard typeName(String name) {
        type(CATALOG_NAME, name);
        return this;
    }

    public NewCatalogCard sendCoverPhoto() {
        type(CATALOG_COVER, "src\\test\\java\\tests\\resources\\noise.jpg");
        return this;
    }

    public GroupProductsPage clickSave() {
        click(SAVE_BUTTON);
        return new GroupProductsPage();
    }
}
