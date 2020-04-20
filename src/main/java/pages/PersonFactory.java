package pages;

public class PersonFactory {
    private PersonFactory() {}

    public static PersonPage getNewPersonFactory() {
        return new PersonPage();
    }
}
