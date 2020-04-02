import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestAddFriend extends TestBase {
    private String url;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        url = "https://ok.ru/";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        login();
    }

// ".//*[contains(@class, 'mctc_navMenu')]//*[contains(@href, 'friends')]"
    @Test
    public void checkFriend() {
        setUp();
        driver.findElement(By.xpath("//*[@id=\"topPanel\"]/div[1]/div[1]/div/ul/li[4]")).click();
        driver.findElement(By.xpath("//*[@id=\"hook_Loader_MyFriendsSquareCardsPagingBLoader\"]/ul/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"hook_Block_MiddleColumnTopCard_MenuFriend\"]/div/a[2]")).click();
        WebElement friend = driver.findElement(By.xpath("//*[@id=\"listBlockPanelFriendsPageMRB\"]/div/div/div/div"));
        friend.findElement(By.xpath("//*[@id=\"listBlockPanelFriendsPageMRB\"]/div/div/div/div[1]/div/div/div[2]/div[4]/span")).click();
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id=\"listBlockPanelFriendsPageMRB\"]/div/div/div/div[1]/div/div/div[2]/div[5]")));
        //for (WebElement element : friends) {

        //}
    }

    private void login() {
        driver.findElement(By.id("field_email")).clear();
        driver.findElement(By.id("field_email")).sendKeys("+79502225459");
        driver.findElement(By.id("field_password")).clear();
        driver.findElement(By.id("field_password")).sendKeys("ShowMustGoOn");
        driver.findElement(By.xpath(".//input[contains(@data-l,'sign_in')]")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
