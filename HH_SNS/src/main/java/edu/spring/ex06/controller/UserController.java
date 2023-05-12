package edu.spring.ex06.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.service.UserInfoService;
import edu.spring.ex06.util.MediaUtil;

@Controller
@RequestMapping(value="/user")
public class UserController {
	private static final Logger logger = 
			LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserInfoService userInfoservice;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	} // 폼에서 전달되는 String형의 데이터를 VO의 Date형의 변수와 매핑되도록 Date형으로 변환 
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@GetMapping("/login")
	public void loginGET() {
		logger.info("loginGET() 호출");
	}
	@PostMapping("/login")
	public String loginPOST(Model model, String userId, String userPassword, HttpServletRequest request ) {
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
			return "redirect:/feed/main";
		}
		else {
			return "redirect:/user/login";
		}
			
			
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
			return "redirect:/feed/main";
		} else {
			return "redirect:/feed/main";
		}
	}
	
	@GetMapping("/profileEdit")
	public void profileEditGET(Model model, HttpServletRequest request) {
		logger.info("profileEdit() 호출");
		HttpSession session =request.getSession();
		String userId = (String) session.getAttribute("userId");
		UserInfoVO vo = userInfoservice.read(userId);
		model.addAttribute("vo",vo);
	}
	
	@PostMapping("/profileEdit")
	public String profileEdit(UserInfoVO vo, MultipartFile file ) {
		logger.info("profileEdit() 호출");
		logger.info("vo.getUserProfile() : " + vo.getUserProfile());
		logger.info("vo.getUserNickname() : " + vo.getUserNickname());
		logger.info("file : "+ file.getSize());
		
		if(file.getSize()>0) { //사진파일의 사이즈가 0이상 경우 파일로 간주하여 파일업로드 진행  (현재 파일을 추가안하는 경우에 null로 넘어오지 않음)
			String savedFileName = saveUploadFile(file);
			vo.setUserProfile(savedFileName);
			logger.info("file 추가 하겠습니다.");
			logger.info("vo.getUserProfile() : " + vo.getUserProfile());
		}
		int result = userInfoservice.updateProfile(vo);

		if(result == 1) {
			logger.info("프로필 업데이트 성공");
		}
		return "redirect:/feed/main";
	}
	private String saveUploadFile(MultipartFile file) {
		// UUID : 업로드하는 파일 이름이 중복되지 않도록 값 생성
		UUID uuid = UUID.randomUUID();
		String savedName = uuid + "_" + file.getOriginalFilename();
		File target = new File(uploadPath, savedName);
		
		try {
			FileCopyUtils.copy(file.getBytes(), target);
			logger.info("파일 저장 성공");
			return savedName;
		} catch (IOException e) {
			logger.error("파일 저장 실패");
			return null;
		}
		
	}
	// display 함수를 호출하면 서버에서 이미지를 확인할 수 있음
			// - 파일 경로를 전송해야 함
			@GetMapping("/display")
			public ResponseEntity<byte[]> display(String fileName) {
				logger.info("display() 호출");
				logger.info(fileName);
				ResponseEntity<byte[]> entity = null;
				InputStream in = null;
				
				String filePath = uploadPath +'/'+ fileName;
				try {
					in = new FileInputStream(filePath);
					
					// 파일 확장자
					String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
					logger.info(extension);
					
					// 응답 헤더(response header)에 Content-Type 설정
					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setContentType(MediaUtil.getMediaType(extension));
					// 데이터 전송
					entity = new ResponseEntity<byte[]>(
								IOUtils.toByteArray(in), // 파일에서 읽은 데이터
								httpHeaders, // 응답 헤더
								HttpStatus.OK
							);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return entity;
			}
	

}
