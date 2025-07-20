package mb.projects.book_market.Enums;

public enum Role {
    ADMIN("Admin"),
    STANDARD("Standard User");
    
    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
}