package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FriendsPage extends HelperBase {
    public FriendsPage(WebDriver driver) {
        super(driver);
    }

    //todo настроить xpath на игнорирование Людей, которых вы можете знать
    public List<WebElement> getListOfMyFriends() {
        return driver.findElements(By.xpath(".//*[@id=\"hook_Loader_MyFriendsSquareCardsPagingBLoader\"]//li[@class=\"ugrid_i\"]"));
    }

    //todo здесь xpath выглядит хреново
    public List<WebElement> getListOfFriendsName() {
        return driver.findElements(By.xpath(".//*[@class=\"hookBlock\" and contains(@id, \"Friends\")]//*[@class=\"ugrid_i\"]//*[@class=\"ucard-w_t ellip-i\"]"));
    }


}
