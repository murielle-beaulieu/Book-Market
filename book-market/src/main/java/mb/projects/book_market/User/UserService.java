package mb.projects.book_market.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository repo;
    ModelMapper mapper;

    public UserService(UserRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public List<User> getAllActiveUsers() {
        List<User> allUsers = getAllUsers();
        return allUsers.stream().filter(user -> user.getIsBanned().equals(Boolean.FALSE) && user.getIsDeleted().equals(Boolean.FALSE)).collect(Collectors.toList());
    }

    public User getUserById(Long id) throws Exception {
        Optional<User> found = this.repo.findById(id);
        if (found.isEmpty()) {
            throw new Exception("No user match id: " + id);
        }
        User result = found.get();

        return result;
    }

    public User getByEmail(String email) {
        Optional<User> result = this.repo.findByEmail(email);
        if (result.isEmpty()) {
            return null;
        }
        User found = result.get();
        return found;
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
        repo.save(result);
        return "Successfully deleted User with ID: " + id;
    }

    public String banUser(Long id, UserBanDTO userBanDTO) {
       Optional<User> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        User result = found.get();
        result.setIsBanned(Boolean.TRUE);
        result.setCause(userBanDTO.getCause());
        result.setNotesAboutBan(userBanDTO.getNotesAboutBan());
        repo.save(result);

        return "Successfully banned User with ID: " + id + "due to " + userBanDTO.getCause().getDisplayName()+" notes: " + userBanDTO.getNotesAboutBan();
    }

}
