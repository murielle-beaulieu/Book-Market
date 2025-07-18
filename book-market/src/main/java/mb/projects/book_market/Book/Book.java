package mb.projects.book_market.Book;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mb.projects.book_market.Enums.BookCondition;
import mb.projects.book_market.Enums.BookGenre;
import mb.projects.book_market.User.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    public Book(User user, String title, String synopsis, List<BookGenre> bookGenres, String author, String yearOfPublication,
            String publisher, String imageLink, BookCondition bookCondition) {
        this.user = user;
        this.title = title;
        this.synopsis = synopsis;
        this.bookGenres = bookGenres;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.publisher = publisher;
        this.imageLink = imageLink;
        this.bookCondition = bookCondition;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true) // references the relationship with the user id, instead of the whole
                                              // object
    private User user;

    @Column
    private String title;

    @Column
    private String author;

    @Column(length = 750)
    private String synopsis;

    @Column
    @ElementCollection(targetClass = Enum.class)
    private List<BookGenre> bookGenres;

    @Column
    private String yearOfPublication;

    @Column
    private String publisher;

    @Column
    private String imageLink;

    @Enumerated(EnumType.ORDINAL)
    private BookCondition bookCondition;

    @Column
    private Boolean isDeleted = Boolean.FALSE;

    @Column
    private Boolean offeredInTrade = Boolean.FALSE;

    @Column
    private Boolean receivedFromTrade = Boolean.FALSE;

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastUpdatedOn;
}
