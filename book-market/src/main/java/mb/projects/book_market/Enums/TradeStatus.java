package mb.projects.book_market.Enums;

public enum TradeStatus {
    PENDING("Pending"),
    ACCEPTED("Accepted"),
    DENIED("Denied");

    private final String displayName;

    TradeStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
