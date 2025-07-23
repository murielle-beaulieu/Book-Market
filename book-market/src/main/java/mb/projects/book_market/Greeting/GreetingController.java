package mb.projects.book_market.Greeting;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mb.projects.book_market.Enums.Role;
import mb.projects.book_market.Role.AllowedRoles;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

	private final GreetingService service;
	
	public GreetingController(GreetingService service) {
		this.service = service;
	}

	@GetMapping("/private")
	public ResponseEntity<String> sayHello() {
		return new ResponseEntity<String>("Hello from a protected endpoint",
				HttpStatus.OK);
	}

	@GetMapping("/public")
	public ResponseEntity<String> sayHelloToEveryone() {
		return new ResponseEntity<String>("Hello from a public endpoint",
				HttpStatus.OK);
	}

	@AllowedRoles({Role.ADMIN})
	@GetMapping("/admin-only")
	public String adminOnly(){
		return service.helloAdmin();
	}

}
