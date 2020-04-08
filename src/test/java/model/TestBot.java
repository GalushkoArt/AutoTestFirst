package model;

import java.util.ResourceBundle;

public abstract class TestBot {
    public final static String RESOURCE_PATH = "resources.";
    protected ResourceBundle botResource;
    protected String botName;

    public TestBot(ResourceBundle botResource, String botName) {
        this.botResource = botResource;
        this.botName = botName;
    }

    public String getBotName() {
        return botName;
    }

    public String getUsername() {
        return botResource.getString("username");
    }

    public String getPassword() {
        return botResource.getString("password");
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }
}
