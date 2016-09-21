package spockboot.it;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import spockboot.domains.User;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {
	
	private static final Logger logger = LoggerFactory.getLogger(UserControllerIT.class);
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    
    @Test
    public void getUser() throws Exception {
        ResponseEntity<User> response = 
        		template.getForEntity(serviceUri("/1/users/12345"), User.class);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
    }
    
    
    @Test
    public void createUser() throws Exception{
    	
    	
    	User user = new User();
    	user.setUserName("test@123.com");
    	user.setFirstName("John");
    	user.setLastName("Simons");
    	user.setEmail("test@123.com");
    	user.setMobileNumber("7325551234");
    	
    	logger.info("user to be created:" + user.toString());
    	
    	HttpEntity<User> entity = new HttpEntity<User>(user);
    	ResponseEntity<String> response = 
    			template.exchange(serviceUri("/1/users/"), HttpMethod.POST, entity, String.class);
    	logger.info("response status: " + response.getStatusCode());
    	logger.info("response body: " + response.getBody());
    	assertTrue(response.getStatusCode() == HttpStatus.CREATED);
    }
    
    @Test
    public void createUserMissingFirstName() throws Exception{
    	User request = new User();
    	request.setLastName("Simons");
    	request.setEmail("test@123.com");
    	
    	ResponseEntity<String> response = 
    			template.postForEntity(serviceUri("/1/users/"), request, String.class);
    	assertTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
    	logger.info("response body: " + response.getBody());
    }
    
    
    public URI serviceUri(String path) throws URISyntaxException{
    	return new URI("http://localhost:" + port + path);
    }

}
