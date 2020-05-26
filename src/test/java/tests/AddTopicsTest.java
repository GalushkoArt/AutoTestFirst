package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.GeoCard;
import pages.GroupPage;
import pages.MusicCard;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddTopicsTest extends TestBase {

    @BeforeEach
    public void openGroup(){
        new GroupPage().openGroupPage();
    }

    @Test
    public void sendMusicTopicTest() throws Exception {
        MusicCard musicCard = new MusicCard("Enter Sandman");
        musicCard.getMusicLayer().choseMusic().shareMusic();
        assertTrue($(musicCard.getMusicNameInFeed()).isDisplayed());
    }

    @Test
    public void sendGeoTopicTest() throws Exception {
        GeoCard geoCard = new GeoCard("SPBPU");
        geoCard.getGeoLayer().chosePlace().confirmPlace().shareGeo();
    }

    @AfterEach
    public void deleteLastTopic(){
        //TODO доделать удаление верхнего элемента
//        $(By.xpath(".//div[@class= 'feed_menu']")).hover();
//        $(By.xpath(".//i[contains(@class, 'ic_delete')]")).click();
    }
}
