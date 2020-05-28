package pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;

import java.util.Map;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class NewGroupCard extends BasePage {
    private static final String GROUP_TYPE_WITH_DATA = ".//*[contains(@data-l, 'createGroupDialog')]//*[contains(@data-l, \"%s\")]";
    private static final String GROUP_CATEGORY_WITH_TEXT = ".//option[contains(text(), \"%s\")]";

    private static final By GROUP_NAME_FIELD = xpath(".//input[contains(@id, 'field_name')]");
    private static final By GROUP_DESCRIPTION_FIELD = xpath(".//*[contains(@id, 'field_description')]");
    private static final By CREATE_GROUP_BUTTON = xpath(".//*[contains(@data-l, 'confirm')]");
    public static final By CREATE_GROUP_CARD = xpath(".//*[@class = 'modal-new_hld']");


    public enum groupTypes {
        PUBLIC,
        COMPANY,
        PLACE,
        PERSON,
        COMMUNICATION,
        EVENT,
        ANNOUNCEMENT,
        SHOP
    }

    private static final Map<groupTypes, String> GROUP_TYPES_STRINGS  = ImmutableMap.<groupTypes, String>builder()
            .put(groupTypes.PUBLIC,        "PAGE")
            .put(groupTypes.COMPANY,       "BRAND")
            .put(groupTypes.PLACE,         "LOCAL")
            .put(groupTypes.PERSON,        "STAR")
            .put(groupTypes.COMMUNICATION, "INTEREST")
            .put(groupTypes.EVENT,         "HAPPENING")
            .put(groupTypes.ANNOUNCEMENT,  "BOARD")
            .put(groupTypes.SHOP,          "SHOP")
            .build();

    public NewGroupCard chooseGroupType(groupTypes type) {
        String typeString = GROUP_TYPES_STRINGS.get(type);
        By locator = xpath(format(GROUP_TYPE_WITH_DATA, typeString));
        click(locator);
        return this;
    }

    public NewGroupCard typeGroupName(String name) {
        type(GROUP_NAME_FIELD, name);
        return this;
    }

    public NewGroupCard typeGroupDescription(String description) {
        type(GROUP_DESCRIPTION_FIELD, description);
        return this;
    }

    public GroupPage create() {
        click(CREATE_GROUP_BUTTON);
        return new GroupPage();
    }
}
