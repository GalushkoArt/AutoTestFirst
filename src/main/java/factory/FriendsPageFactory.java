package factory;

import org.openqa.selenium.By;
import pages.BaseFriendsPage;
import pages.FriendsPage;
import pages.MyFriendsPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class FriendsPageFactory {
    private static final By NAME_OF_FRIENDS_OWNER = By.xpath(".//*[@class = 'compact-profile']//*[text()]");
    private static final By ACCOUNT_OPTIONS = By.xpath(".//*[contains(@class, 'toolbar_dropdown_w')]");
    private static final By NAME_OF_USER = By.xpath(".//*[@class = 'toolbar_dropdown']//*[contains(@class, 'ucard-mini_cnt_i')]");

    public static BaseFriendsPage getFriendsPage() {
        String owner = $(NAME_OF_FRIENDS_OWNER).getText();
        $(ACCOUNT_OPTIONS).click();
        String user = $(NAME_OF_USER).waitUntil(appear, 5000, 400).getText();

        if (owner.equals(user)) {
            return new MyFriendsPage();
        } else {
            return new FriendsPage();
        }
    }
}
