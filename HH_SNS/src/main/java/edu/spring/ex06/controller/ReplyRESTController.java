package edu.spring.ex06.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.ReplyVO;
import edu.spring.ex06.service.FeedService;
import edu.spring.ex06.service.ReplyService;

// * RESTful url과 의미
// /replies (POST) : 댓글 추가(insert)
// /replies/all/숫자 (GET) : 해당 글 번호(boardId)의 모든 댓글 검색(select)
// /replies/숫자 (PUT):  해당 댓글 번호(replyId)의 내요을 수정(update)
// /replies/숫자 (DELETE) : 해당 댓글 번호(replyId)의 댓글을 삭제(delete)

@RestController
@RequestMapping(value="/replies")
public class ReplyRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(ReplyRESTController.class);
	
	@Autowired // ReplyService 주입
	private ReplyService replyService;
	
	@GetMapping("/all/{feedId}") //  댓글 가져오기 GET
	public ResponseEntity<List<ReplyVO>> readReplies(
			@PathVariable("feedId") int feedId) {
		// @pathVariable("feedId") : /all/{feedId} 값을 설정된 변수에 저장
		logger.info("readReplies() 호출 : feedId  = " + feedId);
		List<ReplyVO> list = replyService.read(feedId); // 댓글 가져오기 서비스
		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK); // 댓글 데이터 전달
	}
	
	@PutMapping("/{replyId}") // 댓글 수정 PUT
	public ResponseEntity<Integer> updateReply(
			@PathVariable("replyId") int replyId,
			@RequestBody String replyContent){
		int result = replyService.update(replyId, replyContent); // 댓글 수정 서비스
		return new ResponseEntity<Integer>(result, HttpStatus.OK); // 댓글 수정 상태 전달
	}
	
	@PostMapping // 댓글 생성 POST
	public ResponseEntity<Integer>createReply(@RequestBody Map<String,Object> param){
		logger.info("createReply() 호출 : vo = " + param.toString());
		int feedId = Integer.parseInt((String) param.get("feedId"));
		String userId =(String) param.get("userId");
		String userNickname=(String) param.get("userNickname");
		String replyContent=(String) param.get("replyContent");
		String userProfile=(String) param.get("userProfile");
		String feedUserId=(String) param.get("feedUserId");
		
		ReplyVO vo = new ReplyVO(0, feedId, userId, userNickname, userProfile, replyContent, null, feedId, feedId);
		int result = 0;
		try {
			result = replyService.create(vo,feedUserId); //댓글 생성 서비스
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK); // 댓글 생성 상태 전달
	}
	
	@DeleteMapping("/{replyId}") // 댓글 삭제 DELET
	public ResponseEntity<Integer> deleteReply(
			@PathVariable("replyId") int replyId,
			@RequestBody int feedId){
		logger.info("replyId = " + replyId);
		int result = 0;
		try {
			result = replyService.delete(replyId, feedId); // 댓글 삭제 서비스
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result,HttpStatus.OK); // 댓글 삭제 상태 전달
		
	}
} // ReplyRESTcontroller 
