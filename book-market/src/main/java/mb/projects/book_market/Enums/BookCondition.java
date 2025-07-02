package mb.projects.book_market.Enums;

public enum BookCondition {
    LIKE_NEW("Like New"),
    GREAT("Great"),
    GOOD("Good");

    private final String displayName;

    BookCondition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}