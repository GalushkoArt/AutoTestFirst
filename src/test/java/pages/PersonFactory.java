package pages;

import org.openqa.selenium.WebDriver;

public class PersonFactory {
    private PersonFactory() {}

    public static PersonPage getNewPersonFactory(WebDriver driver) {
        return new PersonPage(driver);
    }

}
