package pages;

import org.openqa.selenium.By;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class MusicSectionPage extends BasePage {
    private static final By MUSIC = xpath(".//div[contains(@class, 'track-two-lines_hld')]"); // вытаскивает 1-ю аудио
    private static final By ADD_MUSIC = xpath(".//a[text()= 'Add']");

    String musicName;
    private By musicNameInFeed;
    private By musicByName;

    public MusicSectionPage(String musicName) {
        this.musicName = musicName;
        this.musicNameInFeed = xpath(format(".//a[text()=\"%s\"]", musicName));
        this.musicByName = xpath(format(".//span[text()=\"%s\"]", musicName));
    }

    public String getMusicNameInFeed() {
        return getText(musicNameInFeed);
    }

    public NewTopicPage choseMusicWithName() {
        click(musicByName);
        click(ADD_MUSIC);
        return new NewTopicPage();
    }

    public NewTopicPage choseFirstMusic() {
        click(MUSIC);
        click(ADD_MUSIC);
        return new NewTopicPage();
    }
}
