package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserMainPage extends HelperBase {

    public static final By GO_TO_FREAND_PAGE = By.xpath(".//*[@class=\"toolbar_nav\"]/*[@data-l=\"t,friends\"]");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    public void goToPageFriends() {
        driver.findElement(GO_TO_FREAND_PAGE).click();
    }
}
