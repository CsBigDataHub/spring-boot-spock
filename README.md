# Spring Boot And Spock
I love Spring Boot and Spock. This sample project is to show how to take advantages of both frameworks.

# Spring Boot
[Spring Boot](http://projects.spring.io/spring-boot/) is great framework. Not much to say. 


# Spock
[Spock](http://spockframework.org/) is a testing and specification framework for Java and Groovy applications. Compared to other testing framework, Spock is really fastinating. 

For example, Data Driven Testing (http://spockframework.org/spock/docs/1.1-rc-2/data_driven_testing.html) is great way to exercise a feature method with a fixed set of data values. Instead of write multiple test cases to test different input values, 'Data Tables' allows developer to write a single test method with a simple input table to verify different scenarios. This is definitely top reason I use Spock.

Another cute feature is that the [Feature Methods](http://spockframework.org/spock/docs/1.1-rc-2/spock_primer.html) can be named with String literals. 

The following is an example to test a User creation API to validate the input. A single test method covers all BAD_REQUEST scenario. Beyond that if the validation rule changes, we simple adjust the input value. 

```
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
		field				      | value
		'firstName'			  | null
		'firstName'			  | ''
		'lastName'			  | null
		'lastName'			  | ''
		'email'				    | null
		'email'				    | ''
		'email'				    | 'notanemailaddress'
		'mobileNumber'		| null
		'mobileNumber'		| ''
}
```
