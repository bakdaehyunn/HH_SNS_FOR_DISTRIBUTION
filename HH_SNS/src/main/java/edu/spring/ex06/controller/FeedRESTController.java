package edu.spring.ex06.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.service.FeedService;

// * RESTful url과 의미
// /replies (POST) : 댓글 추가(insert)
// /replies/all/숫자 (GET) : 해당 글 번호(boardId)의 모든 댓글 검색(select)
// /replies/숫자 (PUT) : 해당 댓글 번호(replyId)의 내용을 수정(update)
// /replies/숫자 (DELETE) : 해당 댓글 번호(replyId)의 댓글을 삭제(delete)

@RestController
@RequestMapping(value = "/feeds")
public class FeedRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(FeedRESTController.class);
	
	@Autowired
	private FeedService feedService;
	
//	@PostMapping // POST : 댓글 입력 -> 데이터를 넣는 형식
//	public ResponseEntity<Integer> createReply(@RequestBody FeedVO vo) {
//		// @RequestBody
//		// - 클라이언트에서 전송받은 json 데이터를
//		//	 자바 객체로 변환해주는 annotation
//		
//		logger.info("creatReply() 호출 : vo = " + vo.toString());
//		int result = 0; // 예외처리
//		try {
//			result = feedService.create(vo);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//
//	}
	
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
