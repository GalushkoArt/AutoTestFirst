package pages;

import model.TestBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends HelperBase {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void logIn(TestBot bot) {
        type(By.id("field_email"), bot.getUsername());
        type(By.id("field_password"), bot.getPassword());
        click(By.xpath(".//input[contains(@data-l,'sign_in')]"));
    }
}
