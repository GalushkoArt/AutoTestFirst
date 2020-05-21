package tests;

import com.codeborne.selenide.Configuration;
import model.BotFactory;
import model.TestBot;
import org.junit.jupiter.api.BeforeAll;
import pages.LoginPage;
import pages.PersonPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public abstract class TestBase {
    protected static TestBot bot;

    @BeforeAll
    public static void setUp() {
        browser = "chrome";
        Configuration.timeout = 5000;
        baseUrl = "https://ok.ru/";
        bot = BotFactory.getOkBot();
        new PersonPage().openHomePage();
        new LoginPage().logIn(bot);
    }

    protected static String getRandomString() {
        return randomAlphanumeric(5, 40);
    }
}
