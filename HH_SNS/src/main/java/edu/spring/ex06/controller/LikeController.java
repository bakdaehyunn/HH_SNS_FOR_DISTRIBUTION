package edu.spring.ex06.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/like")
public class LikeController {
	private static final Logger logger = LoggerFactory.getLogger(LikeController.class);

	@GetMapping("/detail")
	public void LikeGet() {
		logger.info("★LikeController LikeGet 호출");
	}
	
}
