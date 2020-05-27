package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.element;
import static org.openqa.selenium.By.xpath;

public class GeoTopicElement extends BaseTopic{
    private static final By GEO_BUTTON = xpath(".//div[text() = 'Add location']");
    private static final By SEARCH_FIELD = xpath(".//input[contains(@placeholder,'Location name')]");
    private static final By PLACE_LIST = xpath(".//div[contains(@class,'posting-map_toolbar')]");
    public static final By PLACE_ITEMS_LOCATOR = xpath(".//li[@class= 'suggest_li']");
    public static final By DONE_BUUTOM = xpath(".//a[@data-id='done']");

    String place;
    private By placeInFeed;

    public GeoTopicElement(String place) {
        this.place = place;
    }

    public GeoTopicElement getGeoLayer(){
        selectNewTopicWindows();
        if(isOpendTopicWindow()){
            element(GEO_BUTTON).click();
        }
        return this;
    }

    public GeoTopicElement chosePlace(){
        $(SEARCH_FIELD).sendKeys(place);
        $(PLACE_LIST).findAll(PLACE_ITEMS_LOCATOR).first().click();

        return this;
    }

    public GeoTopicElement confirmPlace(){
        $(DONE_BUUTOM).click();
        return this;
    }

    public GeoTopicElement shareGeo() throws Exception {
        shareTopic();
        return this;
    }
}
