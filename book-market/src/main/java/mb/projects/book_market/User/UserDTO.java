package mb.projects.book_market.User;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mb.projects.book_market.Book.Book;
import mb.projects.book_market.Enums.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String username;

    private UserRole userRole;

    private List<Book> books;

    private Boolean isDeleted = Boolean.FALSE;

}
