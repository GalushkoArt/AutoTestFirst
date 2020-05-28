package pages;

import model.TestBot;
import org.openqa.selenium.By;


public class LoginPage extends BasePage {

    public static final By FIELD_EMAIL = By.cssSelector("input#field_email");
    public static final By FIELD_PASSWORD = By.cssSelector("input#field_password");
    public static final By AUTHENTICATION = By.xpath(".//input[contains(@data-l,'sign_in')]");

    /**
     * login to site with bot's data
     * @param bot bot from BotFactory
     * @return bot's home PersonPage
     */

    public PersonPage logIn(TestBot bot) {
        type(FIELD_EMAIL, bot.getUsername());
        type(FIELD_PASSWORD, bot.getPassword());
        click(AUTHENTICATION);
        waitUntilDisappear(AUTHENTICATION);
        return new PersonPage();
    }
}
