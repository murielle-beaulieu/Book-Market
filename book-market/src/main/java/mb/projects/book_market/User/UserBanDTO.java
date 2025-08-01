package mb.projects.book_market.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mb.projects.book_market.Enums.UserBanCause;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBanDTO {

    private Boolean isBanned;

    private UserBanCause cause;

    private String notesAboutBan;

}