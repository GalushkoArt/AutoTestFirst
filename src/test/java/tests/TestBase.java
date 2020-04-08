package tests;

import model.TestBot;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    protected static WebDriver driver;
    protected static String baseUrl;
    protected TestBot bot;

    @BeforeAll
    public static void setUp() {
        init();
    }

    private static void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void turnOff() {
        driver.quit();
    }
}
