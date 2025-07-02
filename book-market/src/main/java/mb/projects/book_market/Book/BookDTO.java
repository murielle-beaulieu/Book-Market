package mb.projects.book_market.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mb.projects.book_market.Enums.BookCondition;
import mb.projects.book_market.Enums.BookGenre;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private String title;

    private String synopsis;

    private BookGenre bookGenre;

    private String author;

    private String yearOfPublication;

    private String publisher;

    private String imageLink;

    private BookCondition bookCondition;

}
