package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GroupListPage {
    //Создание группы
    private static final By GO_TO_GROUPS = By.xpath(".//a[@data-l='t,userAltGroup']");
    private static final By CREATE_NEW_GROUP_BUTTON = By.xpath(".//div[text()= 'Create a group']");
    private static final By PUBLIC_GROUP = By.xpath(".//div[text()= 'Public page']");
    private static final By NAME_OF_GROUP = By.xpath(".//input[@id= 'field_name']");
    private static final By DESCRIPTION_OF_GROUP = By.xpath(".//textarea[@id= 'field_description']");
    private static final By DONE = By.xpath(".//input[@data-l='t,confirm']");
    // Удаление группы
    private static final By BASE = By.xpath(".//li[contains(@class ,'u-menu_li expand-action-item')]");
    private static final By COMMON_OPTION = By.xpath(".//div[contains(@class,'dropdown_cnt u-menu_lvl2')]");
    private static final By DELETE_ITEM = By.xpath(".//li[contains(@class,'u-menu_li')]");

    private static final String name = "TestGroup" + Math.floor(Math.random()*1000);
    private static final String description = "Some description";

    public GroupListPage openGroupListPage() {
        $(GO_TO_GROUPS).click();
        return this;
    }

    public GroupListPage clickOnCreateNewCroupButton(){
        $(CREATE_NEW_GROUP_BUTTON).click();
        return this;
    }
    public GroupListPage choseTypeOfGroup(){
        $(PUBLIC_GROUP).click();
        return this;
    }
    public GroupListPage completionInfoAboutGroup(){
        $(NAME_OF_GROUP).sendKeys(name);
        $(DESCRIPTION_OF_GROUP).sendKeys(description);
        return this;
    }

    public GroupListPage done(){
        $(DONE).click();
        return this;
    }

    public GroupListPage deleteGroup(){
        $(BASE).hover().click();
        $(BASE).hover().findAll(COMMON_OPTION).last().findAll(DELETE_ITEM).last().click();
        return this;
    }
}
