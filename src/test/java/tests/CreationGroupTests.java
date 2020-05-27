package tests;

import org.junit.jupiter.api.Test;
import pages.GroupPage;
import pages.GroupsPage;
import pages.NewGroupCard;
import pages.NewGroupCard.groupTypes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreationGroupTests extends TestBase {

    @Test
    public void creatingGroupTest() {
        final groupTypes GROUP_TYPE = groupTypes.PUBLIC;
        final String GROUP_NAME = getRandomString();
        final String GROUP_DESCRIPTION = getRandomString();

        NewGroupCard card = GroupsPage.openGroupsPage().pressCreateGroup();
        GroupPage groupPage = card.chooseGroupType(GROUP_TYPE)
                .typeGroupName(GROUP_NAME)
                .typeGroupDescription(GROUP_DESCRIPTION)
                .create();

        assertThat(groupPage.getGroupName(), equalTo(GROUP_NAME));
        assertThat(groupPage.getGroupDescription(), equalTo(GROUP_DESCRIPTION));

        groupPage.deleteGroup(); //сброс
    }
}
