package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.element;
import static org.openqa.selenium.By.xpath;

public class MusicCard extends BaseTopic {

    private static final By ADD_MUSIC_BUTTON = xpath(".//div[text() = 'Add music']");
    private static final By MUSIC = xpath(".//div[contains(@class, 'track-two-lines_hld')]"); // вытаскивает 1-ю аудио
    private static final By ADD_MUSCI = xpath(".//a[text()= 'Add']");

    String musicName;
    private By musicNameInFeed;
    private By musicByName;

    public MusicCard(String musicName) {
        this.musicName = musicName;
        this.musicNameInFeed = xpath(".//a[text()='" + musicName + "']");
        this.musicByName = xpath(".//span[text()='" + musicName +"']");
    }

    public By getMusicNameInFeed() {
        return musicNameInFeed;
    }

    public MusicCard getMusicLayer(){
        selectNewTopicWindows();
        if(isOpendTopicWindow()){
            element(ADD_MUSIC_BUTTON).click();
        }
        return this;
    }

    public MusicCard choseMusic(){
//        element(MUSIC).click(); // если нужна 1-я аудио
        element(musicByName).click(); // если нужна конкретная по имени
        element(ADD_MUSCI).click();
        return this;
    }

    public MusicCard shareMusic() throws Exception {
        shareTopic();
        return this;
    }

    public MusicCard findMusicTopic(){
        findTopic(musicName);
        return this;
    }
}
