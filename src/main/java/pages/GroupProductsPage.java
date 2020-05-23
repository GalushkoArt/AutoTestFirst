package pages;

import org.openqa.selenium.By;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class GroupProductsPage extends HelperBase {
    private static final String PRODUCT_WITH_TITLE = ".//div[@class='market-card_n']//a[text()=\"%s\"]";
    private static final By PLACE_BUTTON = xpath(".//a[contains(@class, 'button') and contains(@href, 'post')]");

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
}
