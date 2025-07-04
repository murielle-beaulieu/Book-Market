package mb.projects.book_market.User;

import java.util.List;

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

    
}
