package edu.spring.ex06.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;

@Controller
@RequestMapping(value = "/feed")
public class FeedController {
	private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

//	------------------------------------------------------------------
	@GetMapping("/main")
	public void mainGET(FeedVO feedvo, UserInfoVO userinfovo, HttpSession session) {
		logger.info("★ FeedControllerGET 호출");
	}

	@GetMapping("/list")
	public void list() {
		logger.info("list() 호출 ");
	}

	@GetMapping("/register")
	public void register() {
		logger.info("register() 호출");
	}

	@GetMapping("/test")
	public void test(String data1, String data2, Model model) {
		logger.info("test() 호출");
		model.addAttribute("data1", data1);
		model.addAttribute("data2", data2);
	}

}
