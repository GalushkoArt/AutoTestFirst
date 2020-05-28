package pages;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class GeoTopicPage extends BasePage {
    private static final By SEARCH_FIELD = xpath(".//input[contains(@placeholder,'Location name')]");
    private static final By PLACE_LIST = xpath(".//div[contains(@class,'posting-map_toolbar')]");
    public static final By PLACE_ITEM = xpath(".//li[@class= 'suggest_li']");
    public static final By DONE_BUUTON = xpath(".//a[@data-id='done']");

    private String place;

    public GeoTopicPage(String place) {
        this.place = place;
    }

    public GeoTopicPage chosePlace() {
        type(SEARCH_FIELD, place);
        chooseFirstElementInList(PLACE_ITEM, PLACE_LIST);
        return this;
    }

    public NewTopicPage confirmPlace(){
        click(DONE_BUUTON);
        return new NewTopicPage();
    }
}
