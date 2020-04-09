package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FriendsPage extends HelperBase {

    public static final By FRIEND_LIST_SELECTOR = By.xpath(".//ul[@class = \"ugrid_cnt\"]//li[@class=\"ugrid_i\"]");
    public static final By FRIENDS_NAME_LOCATOR = By.xpath(".//*[@class = \"ugrid_cnt\"]//*[@class=\"ucard-w_t ellip-i\"]");

    public FriendsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getListOfMyFriends() {
        return driver.findElements(FRIEND_LIST_SELECTOR);
    }

    public List<WebElement> getListOfFriendsName() {
        return driver.findElements(FRIENDS_NAME_LOCATOR);
    }
}
