package tabiMax.auth;

public class RegistrationRequest {
	private String email;
	private String password;
	private String fullName;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
