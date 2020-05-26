package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.google.gson.internal.$Gson$Types;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.element;
import static org.openqa.selenium.By.xpath;

public class GeoCard extends BaseTopic{
    private static final By GEO_BUTTON = xpath(".//div[text() = 'Add location']");
    private static final By SEARCH_FIELD = xpath(".//input[contains(@placeholder,'Location name')]");
    private static final By PLACE_LIST = xpath(".//div[contains(@class,'posting-map_toolbar')]");
    public static final By PLACE_ITEMS_LOCATOR = xpath(".//li[@class= 'suggest_li']");
    public static final By DONE_BUUTOM = xpath(".//a[@data-id='done']");

    String place;
    private By placeInFeed;

    public GeoCard(String place) {
        this.place = place;
    }

    public GeoCard getGeoLayer(){
        selectNewTopicWindows();
        if(isOpendTopicWindow()){
            element(GEO_BUTTON).click();
        }
        return this;
    }

    public GeoCard chosePlace(){
        $(SEARCH_FIELD).sendKeys(place);
        $(PLACE_LIST).findAll(PLACE_ITEMS_LOCATOR).first().click();

        return this;
    }

    public GeoCard confirmPlace(){
        $(DONE_BUUTOM).click();
        return this;
    }

    public GeoCard shareGeo() throws Exception {
        shareTopic();
        return this;
    }
}
