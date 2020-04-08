package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FriendsPage extends HelperBase {
    public FriendsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getListOfMyFriends() {
        return driver.findElements(By.xpath(".//ul[@class = \"ugrid_cnt\"]//li[@class=\"ugrid_i\"]"));
    }

    public List<WebElement> getListOfFriendsName() {
        return driver.findElements(By.xpath(".//*[@class = \"ugrid_cnt\"]//*[@class=\"ucard-w_t ellip-i\"]"));
    }
}
