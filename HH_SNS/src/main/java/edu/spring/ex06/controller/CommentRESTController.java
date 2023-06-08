package edu.spring.ex06.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import edu.spring.ex06.service.CommentInfoService;
import edu.spring.ex06.util.MediaUtil;



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
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
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
	
	@PutMapping("/{commentId}") // PUT : 댓글 수정
	public ResponseEntity<Integer> updateComment(
			@PathVariable("commentId") int commentId,
			@RequestBody String commentContent
			// front에서 json으로 데이터를 가져온다는뜻
			){
		int result = commentService.update(commentId, commentContent);
		logger.info("---------------------------------------------------------------");
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{commentId}") // DELETE
	public ResponseEntity<Integer> deleteFeeds(
			@PathVariable("commentId") int commentId) {
		logger.info("commentId = " + commentId);
		
		int result = 0; // 예외처리
		try {
			result = commentService.delete(commentId);
			logger.info("---------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("display() 호출");
		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = uploadPath + fileName;
		try {
			in = new FileInputStream(filePath);
			
			// 파일 확장자
			String extension =
					filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);
			
			// 응답 해더(response header)에 Content-Type 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));
			
			//데이터 전송
			entity = new ResponseEntity<byte[]>(
						IOUtils.toByteArray(in), // 파일에서 읽은 데이터
						httpHeaders, // 응답 해더
						HttpStatus.OK
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
		
	}
	

} // CommentRESTController 
