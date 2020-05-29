package tests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.GroupPage;
import pages.GroupsPage;
import pages.MessageCard;
import pages.NewGroupPage;
import pages.NewGroupPage.groupTypes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreationGroupTests extends TestBase {

    private static final Logger logger = LoggerFactory.getLogger(CreationGroupTests.class);

    @Test
    public void creatingGroupTest() {
        final groupTypes GROUP_TYPE = groupTypes.PUBLIC;
        final String GROUP_NAME = getRandomString();
        final String GROUP_DESCRIPTION = getRandomString();
        logger.info("Run creation group test with {} type, {} name, {} description", GROUP_TYPE, GROUP_NAME, GROUP_DESCRIPTION);

        NewGroupPage card = GroupsPage.openGroupsPage().pressCreateGroup();
        GroupPage groupPage = card.chooseGroupType(GROUP_TYPE)
                .typeGroupName(GROUP_NAME)
                .typeGroupDescription(GROUP_DESCRIPTION)
                .create();

        assertThat(groupPage.getGroupName(), equalTo(GROUP_NAME));
        assertThat(groupPage.getGroupDescription(), equalTo(GROUP_DESCRIPTION));
        logger.info("Creation group test successful");

        groupPage.deleteGroup(); //сброс
        logger.info("Test restored");
    }
}
