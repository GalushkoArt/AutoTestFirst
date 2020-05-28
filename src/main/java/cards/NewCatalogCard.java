package cards;


import org.openqa.selenium.By;
import pages.GroupProductsPage;

import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.xpath;

public class NewCatalogCard extends BaseCard {
    private static final By CATALOG_COVER = xpath(".//div[contains(@class, '__visible')]/span/input[@type='file']");
    private static final By SAVE_BUTTON = xpath(".//input[@type='submit']");
    private static final By CATALOG_NAME = xpath(".//input[@id='field_name']");

    public NewCatalogCard() {
        super($x(".//div[@class='modal-new_hld']"));
    }

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
