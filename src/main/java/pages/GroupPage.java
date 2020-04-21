package pages;

import com.codeborne.selenide.Selenide;

public class GroupPage extends HelperBase {

    /**
     * opens test group page
     * @return new GroupPage
     */

    public GroupPage openGroupPage() {
        Selenide.open("/group/57637278384344");
        return new GroupPage();
    }
}
