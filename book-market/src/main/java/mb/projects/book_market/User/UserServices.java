package mb.projects.book_market.User;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    UserRepository repo;
    ModelMapper mapper;
    
    public UserServices(UserRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        User result = found.get();

        return result;
    }

    public User createUser(UserDTO userDTO) {
        User newUser = mapper.map(userDTO, User.class);
        repo.save(newUser);
        return newUser;        
    }

    public User updateUser(Long id, UpdateUserDTO updateUserDTO) {
        Optional<User> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        User result = found.get();
        mapper.map(updateUserDTO, result);
        System.out.println(updateUserDTO);
        repo.save(result);
        return result;
    }

    public String deleteUser(Long id) {
        Optional<User> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        User result = found.get();
        result.setIsDeleted(true);
        return "Successfully deleted User with ID: " + id;
    }
    
}
