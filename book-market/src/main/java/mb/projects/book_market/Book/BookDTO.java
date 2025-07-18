package mb.projects.book_market.Book;

import java.util.List;

import jakarta.validation.constraints.NotNull;
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

    private List<String> bookGenre;

    private String author;

    private String yearOfPublication;

    private String publisher;

    private String imageLink;

    private BookCondition bookCondition;

    @NotNull
    private Long user_id;

}
