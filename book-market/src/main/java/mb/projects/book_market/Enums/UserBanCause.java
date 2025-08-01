package mb.projects.book_market.Enums;

public enum UserBanCause {
    ANTISOCIAL_BEHAVIOUR("Antisocial Behaviour"),
    FRAUDULENT_ACTIVITY("Fraudulent Activity"),
    HARMFUL_CONTENT("Harmful Content"),
    SPAMMING("Spamming"),
    HARASSMENT("Harassment"),
    VIOLATING_PRIVACY_POLICIES("Violating Privacy Policies");
    
    private final String displayName;

    UserBanCause(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
}
