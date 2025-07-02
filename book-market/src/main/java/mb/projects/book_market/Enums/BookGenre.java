package mb.projects.book_market.Enums;

public enum BookGenre {
    FICTION("Fiction"),
    MYSTERY("Mystery"),
    THRILLER("Thriller"),
    ROMANCE("Romance"),
    FANTASY("Fantasy"),
    SCIENCE_FICTION("Science Fiction"),
    HORROR("Horror"),
    HISTORICAL_FICTION("Historical Fiction"),
    LITERARY_FICTION("Literary Fiction"),
    YOUNG_ADULT("Young Adult"),
    CHILDREN("Children's"),
    NON_FICTION("Non-Fiction"),
    BIOGRAPHY("Biography"),
    AUTOBIOGRAPHY("Autobiography"),
    MEMOIR("Memoir"),
    SELF_HELP("Self-Help"),
    HEALTH_FITNESS("Health & Fitness"),
    BUSINESS("Business"),
    HISTORY("History"),
    SCIENCE("Science"),
    TRAVEL("Travel"),
    COOKING("Cooking"),
    RELIGION_SPIRITUALITY("Religion & Spirituality"),
    PHILOSOPHY("Philosophy"),
    POETRY("Poetry"),
    DRAMA("Drama"),
    CRIME("Crime"),
    ADVENTURE("Adventure"),
    CONTEMPORARY("Contemporary"),
    CLASSIC("Classic");

    private final String displayName;

    BookGenre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}