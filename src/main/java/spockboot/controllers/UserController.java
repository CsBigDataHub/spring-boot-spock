package spockboot.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import spockboot.domains.User;

/**
 * User API
 * @author wguo
 *
 */
@RestController()
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/1/users/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable("userId") String userId){
		logger.info("retrieving user for " + userId);
		return new User();
	}
	
	@RequestMapping(value = "/1/users/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void creatUser(@Valid @RequestBody User user){
		logger.info("creating new user for : " + user.getUserName());
	}
	

}
