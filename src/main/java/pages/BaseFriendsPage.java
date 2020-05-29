package pages;

import com.codeborne.selenide.ElementsCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseFriendsPage extends BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(FriendsPage.class);

    /**
     * returns list of SelenideElement of user's friends
     * @return ElementsCollection
     */

    public abstract ElementsCollection getListOfFriends();

    /**
     * clicks on link with with first name and last name
     * @param firstName person first name
     * @param lastName person last name
     * @return new PersonPage
     */

    public PersonPage clickOnPersonWithName(String firstName, String lastName) {
        findLinkWithText(firstName + " " + lastName).click();
        waitUntilShows(PersonPage.USER_PHOTO);
        logger.info("Went to {} {} page", firstName, lastName);
        return new PersonPage();
    }
}
