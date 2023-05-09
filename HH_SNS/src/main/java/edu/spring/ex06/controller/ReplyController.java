package edu.spring.ex06.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.ex06.service.ReplyService;

@Controller
@RequestMapping(value="/feed")
public class ReplyController {
	private static final Logger logger = 
			LoggerFactory.getLogger(ReplyController.class);
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("/reply")
	public void replyGet() {
		logger.info("replyGET() 호출");
	}
	
}
