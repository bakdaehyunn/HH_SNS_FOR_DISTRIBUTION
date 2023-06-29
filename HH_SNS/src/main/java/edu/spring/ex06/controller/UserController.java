package edu.spring.ex06.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import edu.spring.ex06.service.FollowService;
import edu.spring.ex06.service.UserInfoService;
import edu.spring.ex06.util.MediaUtil;
import edu.spring.ex06.util.PageCriteria;
import edu.spring.ex06.util.PageMaker;

@Controller
@RequestMapping(value="/user")
public class UserController {
	private static final Logger logger = 
			LoggerFactory.getLogger(UserController.class);
	
	@Autowired // UserInfoService 주입
	private UserInfoService userInfoService;
	
	@Autowired// FollowService 주입
	private FollowService followService;
	
	@InitBinder// String형으로 전달 받은 생년월일 데이터를 Date형으로 변환
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	} // 폼에서 전달되는 String형의 데이터를 VO의 Date형의 변수와 매핑되도록 Date형으로 변환 
	
	@Resource(name = "uploadPath") // 이미지저장경로
	private String uploadPath;
	
	@GetMapping("/noti")  //알림 GET
	public void notiGET(){
		logger.info("notiGET() 호출");
		
	}
	

	@GetMapping("/login") // 로그인 GET
	public void loginGET() {
		logger.info("loginGET() 호출");
	}
	@PostMapping("/login") // 로그인 POST
	public String loginPOST( String userId, String userPassword, HttpServletRequest request, RedirectAttributes reAttr ) {
		logger.info("loginPOST() 호출");
		int result = userInfoService.read(userId, userPassword); //회원정보 확인 서비스
		if(result == 1) { // 로그인 성공 시
			logger.info("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);// 아이디를 세션으로 저장
			String targetURL = (String) session.getAttribute("targetURL"); // 세션에서 targetURL 가져오기
			if(targetURL != null) { // authInterceptor에서 세션으로 저장된 경로 가 있다면
				session.removeAttribute("targetURL"); // 세션에 저장된 경로 삭제 
				return "redirect:../" + targetURL; // 지정된 경로로 Redirect
			} else { //저장된 경로가 없다면
				return "redirect:/feed/main"; // main경로로 redirect
			}
		}
		else { // 로그인 실패 시
			reAttr.addFlashAttribute("login_result", "logInUnsuccess"); // 로그인 실패했다는 정보를 redirect하는 경로로 전달
			return "redirect:/user/login"; // 로그인 화면으로 redirect
		}
	}
	
	@GetMapping("/signup")
	public void signupGET() {
		logger.info("signupGET() 호출");
	}
	
	@PostMapping("/signup")
	public String signupPOST(UserInfoVO vo,  RedirectAttributes reAttr) {
		logger.info("signupPOST() 호출");
		
	    
		int result = userInfoService.create(vo); // 회원가입 정보 추가 서비스
		logger.info(result+"개의 계정 생성");
		if(result == 1) { //회원 가입 성공 시
			reAttr.addFlashAttribute("signup_result", "signUpSuccess");// 회원가입 성공했다는 정보를 redirect하는 경로로 전달
			return "redirect:/user/login"; // 로그인 화면으로 redirect
		} 
		return "redirect:/user/signup"; //회원가입 성공하지 않는 경우 회원가입 화면으로 redirect
	}
	
	@GetMapping("/logout") // 로그아웃 GET
	public String logout(HttpServletRequest request) {
		logger.info("logout() 호출");
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) { //세션에 userId 정보가 있을 경우
			session.removeAttribute("userId"); // 세션에 userId 정보 삭제
			return "redirect:/feed/main"; // 메인 화면으로 redirect
		} else { //세션에 userId 정보가 없을 경우
			return "redirect:/feed/main"; // 메인 화면으로 redirect
		}
	}
	
	@GetMapping("/followerlist") // 팔로워리스트 GET
	public void followerlistGET(Model model, String userId, Integer page, Integer numsPerPage) {
		logger.info("followerListGET()");
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		List<UserInfoVO> list = followService.readPagingFollowerList(userId,criteria);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(followService.readFollowerCnt(userId));
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("targetUserId", userId);
	}
	
	@GetMapping("/followinglist") // 팔로잉리스트 GET
	public void followinglistGET(Model model, String userId) {
		logger.info("followingListGET()");
		List<UserInfoVO> list = followService.readFollowingList(userId);
		model.addAttribute("list", list);
	}
	
	@GetMapping("/myAccount") // 회원정보 수정 GET
	public void myAccountGET(Model model, HttpServletRequest request) {
		logger.info("myAccountGET()");
		HttpSession session =request.getSession();
		String userId = (String) session.getAttribute("userId");
		UserInfoVO vo = userInfoService.read(userId);
		model.addAttribute("vo",vo);
	}
	@PostMapping("/myAccount")// 회원정보 수정 POST
	public String  myAccountPOST(UserInfoVO vo,  RedirectAttributes reAttr) {
		logger.info("myAccountPOST()");
		int result = userInfoService.update(vo); // 회원정보 수정 서비스
		logger.info(result+"개의 계정 정보 변경"); 
		if(result == 1) { // 회원정보 수정 성공 시
			return "redirect:/user/profileEdit"; //프로필 편집 화면으로 redirect
		}
		return "redirect:/user/myAccount"; //회원정보 수정 화면으로 redirect
	}
	@GetMapping("/accDelete") // 회원 탈퇴 GET
	public void accDeleteGET() {
		logger.info("accDeleteGET()");
	}
	@PostMapping("/accDelete") // 회원 탈퇴 POST
	public String  accDeletePOST(String userPassword, HttpServletRequest request, RedirectAttributes reAttr) {
		logger.info("accDeletePOST()");
		HttpSession session = request.getSession();
		
		String userId = (String) session.getAttribute("userId");
		int result = userInfoService.read(userId, userPassword); //회원 정보 확인 서비스
		if(result == 1) { // 회원정보가 존재할 경우
			try {
				userInfoService.delete(userId); //회원 탈퇴 서비스
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.removeAttribute("userId"); // 세션에 있는 userId attribute 삭제
			reAttr.addFlashAttribute("delete_result", "accDeleteSuccess"); // 회원이 삭제 되었다는 정보 redirect 경로로 전달
			return "redirect:/user/login"; //로그인 창으로 redirect
		}else { // 회원정보가 틀릴 경우
			reAttr.addFlashAttribute("delete_result", "accDeleteUnSuccess"); //회원 정보가 틀렸다는 정보를 redirect 경로로 전달
			return "redirect:/user/accDelete"; // 회원 탈퇴 화면으로 redirect
		}
	
		
		
	}
	
	@GetMapping("/profileEdit") // 프로필 편집 GET
	public void profileEditGET(Model model, HttpServletRequest request) {
		logger.info("profileEdit() 호출");
		HttpSession session =request.getSession();
		String userId = (String) session.getAttribute("userId");
		UserInfoVO vo = userInfoService.read(userId);
		model.addAttribute("vo",vo);
	}
	
	@PostMapping("/profileEdit")// 프로필 편집 POST
	public String profileEditPOST(UserInfoVO userinfoVO, MultipartFile file,HttpServletRequest request ) {
		logger.info("profileEdit() 호출");
		logger.info("vo.getUserProfile() : " + userinfoVO.getUserProfile());
		logger.info("vo.getUserNickname() : " + userinfoVO.getUserNickname());
		HttpSession session =request.getSession();
		String userId = (String) session.getAttribute("userId");
		logger.info("file : "+ file.getSize());
		
		if(file.getSize()>0) { //프로필사진 파일의 사이즈가 0보다 클 경우 프로필 사진 변경으로 간주하여 프로필 파일 업로드 진행 
			if(userinfoVO.getUserProfile().equals("default.png")) { //회원가입 후 처음으로 프로필 사진 변경 시
				logger.info("회원의 프로필 파일 저장 및 프로필 사진 파일명 변경"); 
				String savedFileName = firstSaveProfileFile(file,userId); //프로필 사진 파일 이름은 UUID + 본인 아이디로 설정된다.
				userinfoVO.setUserProfile(savedFileName); //변경할 프로필사진을 저장하고 프로필 사진 파일명을 변경 한다. (default->유저전용파일명)
				logger.info("vo.getUserProfile() : " + userinfoVO.getUserProfile());
			}else { // 이전에 프로필 변경을 해본 적이 있는 경우
				logger.info("회원의 프로필 파일 저장"); 
				saveProfileFile(file, userinfoVO.getUserProfile());//변경할 프로필 사진을 덮어쓰기로 저장
			}
		} 
		
		int result=0;
		try {
			result = userInfoService.updateProfile(userinfoVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result == 1) {
			logger.info("프로필 업데이트 성공");
		}
		return "redirect:/feed/mylist?userId="+userinfoVO.getUserId();
	}
	private void saveProfileFile(MultipartFile file,String userProfile) {
		File target = new File(uploadPath, userProfile);
		
		try {
			FileCopyUtils.copy(file.getBytes(), target);
			logger.info("파일 저장 성공");
		} catch (IOException e) {
			logger.error("파일 저장 실패");
		}
	}

	private String firstSaveProfileFile(MultipartFile file,String userId) {
		// UUID : 업로드하는 파일 이름이 중복되지 않도록 값 생성
		UUID uuid = UUID.randomUUID();
		file.getOriginalFilename();
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		logger.info(extension);
		String savedName = uuid + "_" + userId+"."+ extension;
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
