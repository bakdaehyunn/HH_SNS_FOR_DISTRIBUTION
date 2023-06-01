package edu.spring.ex06.controller;

import java.util.List;

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

import edu.spring.ex06.domain.CommentInfoVO;
import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.ReplyVO;
import edu.spring.ex06.service.CommentInfoService;
import edu.spring.ex06.service.FeedService;
import edu.spring.ex06.service.ReplyService;



// * RESTful url과 의미
// /replies (POST) : 댓글 추가(insert)
// /replies/all/숫자 (GET) : 해당 글 번호(boardId)의 모든 댓글 검색(select)
// /replies/숫자 (PUT):  해당 댓글 번호(replyId)의 내요을 수정(update)
// /replies/숫자 (DELETE) : 해당 댓글 번호(replyId)의 댓글을 삭제(delete)

@RestController
@RequestMapping(value="/comments")
public class CommentRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(CommentRESTController.class);
	
	@Autowired
	private CommentInfoService commentService;
	
	@PostMapping // POST : 댓글 입력
	public ResponseEntity<Integer> createComment(@RequestBody CommentInfoVO vo){
		// @RequestBody
		// - 클라이언트에서 전송받은 json 데이터를 
		//   자바 객체로 변환해주는 annotation
		logger.info("★ CommentRestController 대댓글 등록 : " + vo.toString());
		
		// ResponseEntity<T> : Rest 방식에서 데이터를 리턴할 때 쓰이는 객체
		// - 데이터 HttpStatus를 전송
		// - <T> : 보내고자 하는 데이터 타입
		
		int result = 0;
		try {
			result = commentService.create(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all/{replyId}") 
	public ResponseEntity<List<CommentInfoVO>> readComment(
			@PathVariable("replyId") int replyId) {
		// PathVariable("boardId") : /all/{boardId} 값을 설정된 변수에 저장
		// 실제로 할 때는 /all/1 -> 이런식으로 한당 ㅎㅅㅎ
		logger.info("★ CommentRestController 전체검색 : " + replyId);
		
		List<CommentInfoVO> list = commentService.read_all(replyId);
		logger.info("---------------------------------------------------------------");
		return new ResponseEntity<List<CommentInfoVO>>(list, HttpStatus.OK);
	}
	

} // CommentRESTController 
