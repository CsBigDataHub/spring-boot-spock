package spockboot.it

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import spock.lang.Unroll
import spockboot.domains.User

class UserControllerSpecs extends ItBase {
	
	
	@Unroll
	def "retrieve user by userId #userId" (String userId){
		
		given:
			String uri = "/1/users/${userId}"
		when:
			ResponseEntity<User> response = template.getForEntity(serviceUri(uri), User.class)
		then:
			response.getStatusCode() == HttpStatus.OK
		where:
			userId			| _
			'12345'			| _
			"abcded"		| _
	}
	
	
	def "create new user sunny" () {
		given:
			User user = new User()
			user.userName = "john.simons@abc.com"
			user.firstName = "John"
			user.lastName = "Simons"
			user.email = "john.simons@abc.com"
			user.mobileNumber = "17325551234"
		when:
			ResponseEntity<String> response =
				template.postForEntity(serviceUri("/1/users/"), user, String.class)
		then:
			response.getStatusCode() == HttpStatus.CREATED
	}
	
	
	@Unroll
	def "create user missing required field #field --> #value"(String field, String value){
		given:
			User user = new User()
			user.userName = "john.simons@abc.com"
			user.firstName = "John"
			user.lastName = "Simons"
			user.email = "john.simons@abc.com"
			user.mobileNumber = "17325551234"
			
			// set a given field to the test value
			user."${field}" = value
		when:
			ResponseEntity<String> response =
				template.postForEntity(serviceUri("/1/users/"), user, String.class)
		then:
			response.getStatusCode() == HttpStatus.BAD_REQUEST
		where:
			field				| value
			'firstName'			| null
			'firstName'			| ''
			'lastName'			| null
			'lastName'			| ''
			'email'				| null
			'email'				| ''
			'email'				| 'notanemailaddress'
			'mobileNumber'		| null
			'mobileNumber'		| ''
	}

}
