package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

public class MyFriendsPage extends BaseFriendsPage {
    private static final By SENT_REQUESTS_PAGE = By.xpath(".//a[contains(@href, 'outgoingFriendRequests')]");
    private static final By FRIEND_LIST = By.xpath(".//ul[@class = \"ugrid_cnt\"]//li[@class=\"ugrid_i\"]");

    @Override
    public ElementsCollection getListOfFriends() {
        return getListOfElements(FRIEND_LIST);
    }

    /**
     * goes to sent requests section
     * @return current FriendsPage
     */

    public MyFriendsPage goToSentRequests() {
        click(SENT_REQUESTS_PAGE);
        return this;
    }
}
