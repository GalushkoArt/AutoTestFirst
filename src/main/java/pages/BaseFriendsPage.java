package pages;

import com.codeborne.selenide.ElementsCollection;
import pages.BasePage;
import pages.PersonPage;

public abstract class BaseFriendsPage extends BasePage {

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
        return new PersonPage();
    }
}
