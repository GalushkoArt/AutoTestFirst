package tests;

import com.codeborne.selenide.Configuration;
import model.TestBot;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.browser;

public abstract class TestBase {
    protected static TestBot bot;

    @BeforeAll
    public static void setUp() {
        init();
    }

    private static void init() {
        browser = "chrome";
        Configuration.timeout = 2000;
    }
}
