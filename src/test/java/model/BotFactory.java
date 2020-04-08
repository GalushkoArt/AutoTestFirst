package model;

public class BotFactory {
    private BotFactory() {}

    public static TestBot getOkBot() {
        return new OkBot();
    }

    public static TestBot getOkBotWithName(String name) {
        return new OkBot(name);
    }
}
