package mb.projects.book_market.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import mb.projects.book_market.Enums.UserRole;
import mb.projects.book_market.User.User;
import mb.projects.book_market.User.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(String... args) throws Exception {


    User user1 = new User("Raj", "Kumar", "rajkumar@bookmarket.com", passwordEncoder.encode("pass"), "raj444");
    user1.setIsDeleted(false);
    user1.setUserRole(UserRole.STANDARD);
    userRepository.save(user1);

    User user2 = new User("Ryan", "Thompson", "ryanthompson@bookmarket.com", passwordEncoder.encode("pass"), "ryry_t");
    user2.setIsDeleted(false);
    user2.setUserRole(UserRole.STANDARD);
    userRepository.save(user2);

    User user3 = new User("Alex","Poirier","alexpoirier@bookmarket.com",passwordEncoder.encode("pass"),"apple_poirier");
    user3.setIsDeleted(false);
    user3.setUserRole(UserRole.STANDARD);
    userRepository.save(user3);

    User user4 = new User("Cheah","Davos","cheahdavos@bookmarket.com",passwordEncoder.encode("pass"),"cheah-12");
    user4.setIsDeleted(false);
    user4.setUserRole(UserRole.STANDARD);
    userRepository.save(user4);

    }

}
