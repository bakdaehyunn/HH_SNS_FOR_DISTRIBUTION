package edu.spring.ex06.controller;

import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import edu.spring.ex06.domain.NotiVO;

import edu.spring.ex06.service.FollowService;
import edu.spring.ex06.service.NotiService;
import edu.spring.ex06.service.UserInfoService;

@RestController
@RequestMapping(value = "/users")
public class UserRESTController {
	private static final Logger logger = 
			LoggerFactory.getLogger(UserRESTController.class);
	
	@Autowired // UserInfoService 주입
	private UserInfoService userinfoService;
	
	@Autowired // FollowService 주입
	private FollowService followService;
	
	@Autowired // NotiService 주입
	private NotiService notiService;
	
	@Autowired // JavaMailSenderImpl 주입
	private JavaMailSenderImpl mailSender;
	
	
	
	private int authNumber; // 인증번호 
	
	@PutMapping("/notiRead")// 알림읽음 PUT
	public ResponseEntity<Integer>readNoti(HttpServletRequest request){
		HttpSession session = request.getSession();
		int result = 0;
		if(session.getAttribute("userId") != null) { // 세션에 userId가 존재할 경우
			String userId = (String) session.getAttribute("userId");
			result= notiService.update(userId); //알림 읽음으로 변경 서비스
		}
		return new ResponseEntity<Integer>(result,HttpStatus.OK); // 알림 변경 상태 전달
	}
	
	@GetMapping("/notiCheck")// 알림 확인 GET
	public ResponseEntity<Integer>checkNoti(HttpServletRequest request){
		HttpSession session = request.getSession();
		int result = 0;
		if(session.getAttribute("userId") != null) { // 세션에 userId가 존재할 경우
			String userId = (String) session.getAttribute("userId");
			result= notiService.readCheck(userId);// 알림 확인 서비스
		}
		return new ResponseEntity<Integer>(result,HttpStatus.OK); // 알림 확인 데이터 전달
	}
	
	@GetMapping("/notiList") // 알림 리스트 GET
	public ResponseEntity<List<NotiVO>>readNotiList(HttpServletRequest request){
		HttpSession session = request.getSession();
		List<NotiVO> list = null ;
		if(session.getAttribute("userId") != null) { // 세션에 userId가 존재할 경우
			String userId = (String) session.getAttribute("userId");
			list= notiService.readList(userId); //회원의 알림 리스트 불러오기 서비스
		}
		return new ResponseEntity<List<NotiVO>>(list,HttpStatus.OK); // 알림 리스트 데이터 전달
	}
	@DeleteMapping("/notiDelete/{notiId}") // 알림 삭제 Delete
	public ResponseEntity<Integer> deleteNoti(@PathVariable("notiId") int notiId){
		int result = notiService.delete(notiId); //알림 삭제 서비스
		return new ResponseEntity<Integer>(result,HttpStatus.OK); // 알림 삭제 상태 전달
	}
	
	@GetMapping("/userId/{userId}") // 아이디 중복체크 GET
	public ResponseEntity<Integer> readUserId(
			@PathVariable("userId") String userId){
		int result = userinfoService.readUserId(userId); // 아이디정보 확인 서비스
		return new ResponseEntity<Integer>(result,HttpStatus.OK); // 아이디 중복 체크 상태 전달
	} 
	
	@GetMapping("/userEmail/{email1}/{email2}")// 이메일 중복 체크 GET
	public ResponseEntity<Integer> readUserEmail(
			@PathVariable("email1") String email1, @PathVariable("email2") String email2){
		String userEmail = email1+"."+email2;
		int result = userinfoService.readUserEmail(userEmail); //이메일정보 확인 서비스
		return new ResponseEntity<Integer>(result,HttpStatus.OK); // 이메일 중복 체크 상태 전달
	}
	
	@PostMapping("/emailVerif") // 이메일 인증 POST
	public ResponseEntity<Integer>emailVerif(@RequestBody String userEmail){
		logger.info("emailVerif()");
		logger.info(userEmail);
		sendEmail(userEmail); // 이메일 전송 메소드
		return new ResponseEntity<Integer>(authNumber,HttpStatus.OK);
	}
	
	@GetMapping("/followCheck/{userinfoUserId}") //팔로우 확인 GET
	public ResponseEntity<Integer> readFollowing(@PathVariable("userinfoUserId") String userinfoUserId, HttpServletRequest request){
		int result=0;
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) { // 세션에 userId 정보가 있을 경우
			String userId = (String) session.getAttribute("userId");
			result = followService.readFollowingCheck(userId,userinfoUserId); // 팔로우 상태 확인 서비스
		}
		return new ResponseEntity<Integer>(result,HttpStatus.OK); // 팔로우 상태 전달
	}
	
	@PostMapping("/follow") // 팔로우 하기 POST
	public ResponseEntity<Integer>follow(@RequestParam("userId") String userinfoUserId, HttpServletRequest request){
		logger.info("follow() ");
		logger.info("userinfoUserId : " + userinfoUserId);
		int result =0;
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) { // 세션에 userId 정보가 있을 경우
			String userId = (String) session.getAttribute("userId"); 
			try {
				result = followService.create(userId, userinfoUserId); // 해당 회원과의 팔로우 서비스
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<Integer>(result,HttpStatus.OK); // 팔로우 하기 상태 전달
	}
	
	@DeleteMapping("/{userinfoUserId}") //  팔로우 취소 DELETE
	public ResponseEntity<Integer> unfollow(@PathVariable("userinfoUserId") String userinfoUserId, HttpServletRequest request){
	
		int result=0;
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) { // 세션에 userId 정보가 있을 경우
			String userId = (String) session.getAttribute("userId");
			try {
				result = followService.delete(userId,userinfoUserId); //해당 유저와의 팔로우 취소 서비스
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<Integer>(result,HttpStatus.OK); // 팔로우 취소 상태 전달
	}	 
	public void sendEmail(String userEmail) { //이메일 전송
		Random r = new Random();
		int makeAuthNum = r.nextInt(888888) + 1111;// 111111 ~ 999999 사이의 랜덤값 생성
		System.out.println("인증번호 : " + makeAuthNum); 
		authNumber = makeAuthNum; // 인증번호로 저장
		String setFrom = "hennei@naver.com"; // 보내는 이메일 주소 저장
		String toMail = userEmail; //받는 이메일 저장
		String title = " 이메일 인증을 위한 인증번호 발급."; // 인증 이메일 제목 저장 
		String content = //HTML 형식의 내용을 저장
				"H&H 소셜 네트워트에 방문하신 것을 환영합니다." + 	 
                "<br><br>" + 
			    "인증 번호는 " + authNumber + "입니다." + 
			    "<br>" + 
			    "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; 
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8"); // utt-8로 인코딩 설정
			helper.setFrom(setFrom);//보내는 이메일 주소 세팅
			helper.setTo(toMail);// 받는 이메일 주소 세팅
			helper.setSubject(title); //제목 세팅
			helper.setText(content,true);// 내용 세팅  true시 html 형식으로 전송 
			mailSender.send(message);// 이메일 전송
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
