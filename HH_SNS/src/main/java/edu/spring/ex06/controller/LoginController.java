package edu.spring.ex06.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.service.UserInfoService;

@Controller
@RequestMapping(value="/user")
public class LoginController {
	private static final Logger logger = 
			LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserInfoService userInfoservice;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	} // 폼에서 전달되는 String형의 데이터를 VO의 Date형의 변수와 매핑되도록 Date형으로 변환 
	
	@GetMapping("/login")
	public void loginGET() {
		logger.info("loginGET() 호출");
	}
	@PostMapping("/login")
	public String loginPOST(String userId, String userPassword, HttpServletRequest request) {
		logger.info("loginPOST() 호출");
		int result = userInfoservice.read(userId, userPassword);
		if(result == 1) {
			logger.info("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			
			// 세션에서 targetURL 가져오기
			//String targetURL = (String) session.getAttribute("targetURL");
		//	if(targetURL != null) {
			//	session.removeAttribute("targetURL");
				//return "redirect:" + targetURL;
			//} else {
				//return "redirect:/feed/list";
			//}
			
		//} else {
			//logger.info("로그인 실패");
		}
			return "redirect:/feed/list";
			
		//}
	}
	
	@GetMapping("/signup")
	public void signupGET() {
		logger.info("signupGET() 호출");
	}
	
	@PostMapping("/signup")
	public String signupPOST(UserInfoVO vo,  RedirectAttributes reAttr) {
		logger.info("signupPOST() 호출");
		
	    
		int result = userInfoservice.create(vo);
		logger.info(result+"개의 계정 생성");
		if(result == 1) {
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/user/login";
		}
		return "redirect:/user/signup";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		logger.info("logout() 호출");
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {
			session.removeAttribute("userId");
			return "redirect:/feed/list";
		} else {
			return "redirect:/feed/list";
		}
	}
	

}
