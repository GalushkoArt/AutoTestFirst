package model;

import java.util.ResourceBundle;

class OkBot extends TestBot {

    OkBot() {
        super(ResourceBundle.getBundle("ok_bot_data"), "Test Bot");
    }

    OkBot(String name) {
        super(ResourceBundle.getBundle("ok_bot_data"), name);
    }
}
