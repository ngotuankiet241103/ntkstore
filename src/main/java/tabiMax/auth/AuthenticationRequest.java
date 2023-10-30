package tabiMax.auth;

import lombok.NonNull;

public class AuthenticationRequest {
	@NonNull
	private String email;
	@NonNull
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmails(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
