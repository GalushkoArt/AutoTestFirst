package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.GroupListPage;

public class CreationGroupTest extends TestBase{

    static GroupListPage groupListPage;

    @BeforeAll
    public static void groupListPageInit(){
        groupListPage = new GroupListPage();
    }

    @Test
    public void createGroupTest() throws InterruptedException {
        groupListPage
                .openGroupListPage()
                .clickOnCreateNewCroupButton()
                .choseTypeOfGroup()
                .completionInfoAboutGroup()
                .done();
        groupListPage
                .deleteGroup()
                .done();
    }
}
