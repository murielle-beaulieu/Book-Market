package mb.projects.book_market.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mb.projects.book_market.User.User;
import mb.projects.book_market.User.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(
			@Valid @RequestBody AuthRegisterDTO data
	) {
		AuthResponse res = this.authService.register(data);
		return new ResponseEntity<AuthResponse>(res, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginDTO data) throws Exception {
		System.out.println(data.getEmail() + " email");
		System.out.println(data.getPassword() + " password");
		return new ResponseEntity<>(authService.login(data), HttpStatus.OK);
	}

	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfile (@RequestBody AuthLoginDTO data ) throws Exception {
		User found = this.userService.getByEmail(data.getEmail());
		return new ResponseEntity<>(found, HttpStatus.OK);
	}
	
}