package pages;

import model.TestBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends HelperBase {

    public static final By FIELD_EMAIL = By.id("field_email");
    public static final By FIELD_PASSWORD = By.id("field_password");
    public static final By AUTHENTICATION = By.xpath(".//input[contains(@data-l,'sign_in')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void logIn(TestBot bot) {
        type(FIELD_EMAIL, bot.getUsername());
        type(FIELD_PASSWORD, bot.getPassword());
        click(AUTHENTICATION);
    }
}
