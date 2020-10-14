package be.abis.exercise.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Login {
	
	@Email(message="A valid email address should be given")
	private String email;
	@NotBlank(message="A password should be given")
	private String psw;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	
	

}
