package mb.projects.book_market.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mb.projects.book_market.Config.JwtService;
import mb.projects.book_market.Enums.UserRole;
import mb.projects.book_market.User.User;
import mb.projects.book_market.User.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authManager;

    public AuthResponse register(AuthRegisterDTO data) {
        User user = new User(data.getFirstName(),
                data.getLastName(),
                data.getEmail(),
                passwordEncoder.encode(data.getPassword()));
        user.setUserRole(UserRole.STANDARD);
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthLoginDTO data) throws Exception {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                data.getEmail(),
                data.getPassword());

        authManager.authenticate(token);
        

        User user = userRepository.findByEmail(data.getEmail()).orElseThrow(
                () -> new Exception());
        String jwtToken = jwtService.generateToken(user);

        System.out.println("Generated token: " + jwtToken); // Debug line

        return new AuthResponse(jwtToken);
    }
}
