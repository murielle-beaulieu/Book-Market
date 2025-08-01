package mb.projects.book_market.User;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mb.projects.book_market.Enums.Role;
import mb.projects.book_market.Role.AllowedRoles;
import mb.projects.book_market.Trade.TradeRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    TradeRepository tradeRepo;
    
    public UserController(UserService userService, TradeRepository tradeRepo) {
        this.userService = userService;
        this.tradeRepo = tradeRepo;
    }

    @AllowedRoles({Role.ADMIN})
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        System.out.println("users: " + allUsers.size());
        return new ResponseEntity<>(allUsers, HttpStatus.OK);      
    }

    @GetMapping("/active")
    public ResponseEntity<List<User>> getAllActiveUsers(){
        List<User> allUsers = userService.getAllActiveUsers();
        System.out.println("users: " + allUsers.size());
        return new ResponseEntity<>(allUsers, HttpStatus.OK);      
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK); 
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody UserDTO UserDTO) {
        User newUser = userService.createUser(UserDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO) {
        User updatedUser = userService.updateUser(id, updateUserDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @AllowedRoles({Role.ADMIN})
    @PatchMapping("/ban/{id}") 
    public String banUser(@PathVariable Long id, @RequestBody UserBanDTO userBanDTO){
        return userService.banUser(id, userBanDTO);
    }

}
