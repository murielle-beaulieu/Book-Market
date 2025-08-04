package mb.projects.book_market.User;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mb.projects.book_market.Book.Book;
import mb.projects.book_market.Enums.Role;
import mb.projects.book_market.Enums.UserBanCause;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String displayUsername;

    private Role role;

    private List<Book> books;

    private Boolean isDeleted = Boolean.FALSE;

    private Boolean isBanned = Boolean.FALSE;

    private UserBanCause cause;

}
