package tests;

import com.codeborne.selenide.Configuration;
import model.TestBot;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.browser;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public abstract class TestBase {
    protected static TestBot bot;

    @BeforeAll
    public static void setUp() {
        init();
    }

    private static void init() {
        browser = "chrome";
        Configuration.timeout = 5000;
    }

    protected static String getRandomString() {
        return randomAlphanumeric(5, 40);
    }
}
