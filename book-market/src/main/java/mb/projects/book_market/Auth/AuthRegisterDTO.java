package mb.projects.book_market.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class AuthRegisterDTO {

	@NotBlank
	@Getter
	@Setter
	private String firstName;

	@Getter
	@Setter
	private String lastName;

	@Email
	@Getter
	@Setter
	@NotBlank
	private String email;

	@NotBlank
	@Getter
	@Setter
	private String password;

	public AuthRegisterDTO() {
	}

	public AuthRegisterDTO(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
}