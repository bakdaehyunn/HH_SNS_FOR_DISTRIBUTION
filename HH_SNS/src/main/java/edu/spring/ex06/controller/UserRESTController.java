package edu.spring.ex06.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.ex06.service.UserInfoService;

@RestController
@RequestMapping(value = "/users")
public class UserRESTController {
	private static final Logger logger = 
			LoggerFactory.getLogger(UserRESTController.class);
	
	@Autowired
	private UserInfoService userinfoService;
	
	@GetMapping("/userId/{userId}")
	public ResponseEntity<Integer> readUserId(
			@PathVariable("userId") String userId){
		int result = userinfoService.readUserId(userId);
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
	
	@GetMapping("/userEmail/{userEmail}")
	public ResponseEntity<Integer> readUserEmail(
			@PathVariable("userEmail") String userEmail){
		int result = userinfoService.readUserEmail(userEmail);
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
}
