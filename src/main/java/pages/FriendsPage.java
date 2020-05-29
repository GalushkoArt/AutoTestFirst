package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import pages.BaseFriendsPage;

public class FriendsPage extends BaseFriendsPage {
    private static final By FRIEND_LIST = By.xpath(".//div[@class=\"ugrid_i\"]");
    public static final By FRIENDS_PAGE = By.cssSelector("div.friends-page");

    @Override
    public  ElementsCollection getListOfFriends() {
        return getListOfElements(FRIEND_LIST);
    }
}
