package spockboot.domains;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class User implements Serializable {

	private static final long serialVersionUID = 9037993669285631487L;
	
	@NotBlank(message = "username cannot be null or blankc")
	private String userName;
	
	@NotBlank(message = "first name cannot be null or blank")
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotBlank @Email
	private String email;
	
	@NotBlank
	private String mobileNumber;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + "]";
	}

}
