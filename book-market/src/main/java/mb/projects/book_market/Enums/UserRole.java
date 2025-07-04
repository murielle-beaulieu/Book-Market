package mb.projects.book_market.Enums;

public enum UserRole {
    ADMIN("Admin"),
    STANDARD("Standard User");
    
    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
}