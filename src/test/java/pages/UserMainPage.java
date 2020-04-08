package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserMainPage extends HelperBase {

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    public void goToPageFriends() {
        driver.findElement(By.xpath(".//*[@class=\"toolbar_nav\"]/*[@data-l=\"t,friends\"]")).click();
    }
}
