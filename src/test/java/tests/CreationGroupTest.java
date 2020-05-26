package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.GroupListPage;

public class CreationGroupTest extends TestBase{

    @Test
    public void createGroupTest(){
        new GroupListPage()
                .openGroupListPage()
                .clickOnCreateNewCroupButton()
                .choseTypeOfGroup()
                .completionInfoAboutGroup()
                .done();
    }

    @Test
    public void deleteGroupTest(){
        //TODO чуть позже доделаю удаление
    }

}
