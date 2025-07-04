package mb.projects.book_market.User;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    UserServices userServices;
    
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userServices.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);      
    }
    
}
