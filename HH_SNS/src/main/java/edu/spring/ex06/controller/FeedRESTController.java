package edu.spring.ex06.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.service.FeedService;
import edu.spring.ex06.service.UserInfoService;

// * RESTful url과 의미
// /replies (POST) : 댓글 추가(insert)
// /replies/all/숫자 (GET) : 해당 글 번호(boardId)의 모든 댓글 검색(select)
// /replies/숫자 (PUT) : 해당 댓글 번호(replyId)의 내용을 수정(update)
// /replies/숫자 (DELETE) : 해당 댓글 번호(replyId)의 댓글을 삭제(delete)

@RestController
@RequestMapping(value = "/feeds")
public class FeedRESTController {
	private static final Logger logger = LoggerFactory.getLogger(FeedRESTController.class);

	@Autowired
	private FeedService feedService;
	
	@Autowired
	private UserInfoService userInfoService;

	@PostMapping // POST : 댓글 입력 -> 데이터를 넣는 형식
	public ResponseEntity<Integer> createFeed(@RequestBody FeedVO feedvo, UserInfoVO userinfovo, HttpSession session) {
		// @RequestBody
		// - 클라이언트에서 전송받은 json 데이터를
		// 자바 객체로 변환해주는 annotation
		logger.info("★ FeedRESTController : " + feedvo.toString());
		// FeedVO [feedId=1, feedContent=ㅁㄴㅇ, userId=, userNickname=null,
		// userProfile=null, replyCount=0, likeCount=0, feedDate=null, musicTitle=null]

		String userid = (String) session.getAttribute("userId");

		// 세션에 저장된 아이디가 유저정보에서 있는지 확인을 하고(db)
		// 있으면 현재 세션 아이디와 유저정보와 일치하는 유저의 닉네임과 프로필을 가져온다

		if (userid != null && userid.contains(feedvo.getUserId())) {
			// 현재 세션 아이디와 유저정보와 일치하는 유저의 닉네임과 프로필 가져오는 코드 작성
			logger.info("현재 세션 아이디 : " + userid);
			logger.info("포함되어 있는 유저 정보 : " + userid.contains(feedvo.getUserId()));
			
			userinfovo = userInfoService.read(userid);
			
			String feedcontent = feedvo.getFeedContent();
			String usernickname = userinfovo.getUserNickname();
			String userprofile = userinfovo.getUserProfile();
			Date feeddate = new Date(); 
			String musictitle = "X";
			feedvo = new FeedVO(0, feedcontent, userid, usernickname, userprofile, 0, 0, feeddate, musictitle);
			logger.info("다시한번 ! : " + feedvo.toString());


		} else {
			// 유저정보가 없는 경우나 세션이 만료된 경우 등에 대한 예외 처리
			logger.info("없음 !");
		}

		int result = 0; // 예외처리
		try {
			result = feedService.create(feedvo, userinfovo, session);
			logger.info("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Integer>(result, HttpStatus.OK);

	}

//	@GetMapping("/all/{boardId}") // GET : 댓글 선택(all)
//	public ResponseEntity<List<ReplyVO>> readReplies(
//			@PathVariable("boardId") int boardId) {
//		// PathVariable("boardId") : /all/{boardId} 값을 설정된 변수에 저장
//		// 실제로 할 때는 /all/1 -> 이런식으로 한당 ㅎㅅㅎ
//		logger.info("readReplies() 호출 : boardId = " + boardId);
//		
//		List<ReplyVO> list = replyService.read(boardId);
//		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
//	}
//	
//	@PutMapping("/{replyId}") // PUT : 댓글 수정
//	public ResponseEntity<Integer> updateReply(
//			@PathVariable("replyId") int replyId,
//			@RequestBody String replyContent
//			// front에서 json으로 데이터를 가져온다는뜻
//			){
//		int result = replyService.update(replyId, replyContent);
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/{replyId}") // DELETE
//	public ResponseEntity<Integer> deleteReply(
//			@PathVariable("replyId") int replyId,
//			@RequestBody int boardId) {
//		logger.info("replyId = " + replyId);
//		
//		int result = 0; // 예외처리
//		try {
//			result = replyService.delete(replyId, boardId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}

}// ReplyRESTcontroller
