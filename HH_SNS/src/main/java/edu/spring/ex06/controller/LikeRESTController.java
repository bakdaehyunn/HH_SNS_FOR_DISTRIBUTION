package edu.spring.ex06.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.LikeInfoVO;
import edu.spring.ex06.service.FeedService;
import edu.spring.ex06.service.LikeInfoService;
import edu.spring.ex06.service.UserInfoService;


@RestController
@RequestMapping(value = "/likes")
public class LikeRESTController {
	private static final Logger logger = LoggerFactory.getLogger(LikeRESTController.class);

	@Autowired
	private FeedService feedService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private LikeInfoService likeInfoService;

	@PostMapping
	public ResponseEntity<Integer> createLike(@RequestBody LikeInfoVO likeinfovo, HttpSession session) {
		// @RequestBody
		// - 클라이언트에서 전송받은 json 데이터를
		// 자바 객체로 변환해주는 annotation
		logger.info("★ LikeRESTController : " + likeinfovo.toString());
		
		// 좋아요 db에 같은 피드 아이디 존재 X
		// 좋아요 db에 같은 유저 아이디 존재 X
		// 즉 이미 좋아요 누른 사람은 X
		
		int result = 0; // 예외처리
		
		int feedId = likeinfovo.getFeedId();
		
		List<LikeInfoVO> list = likeInfoService.read_all(feedId);
		
		for(LikeInfoVO likevo : list) {
			logger.info("좋아요 정보 : " + likevo.toString());
		}
		
		try {
			result = likeInfoService.create(likeinfovo);
			logger.info("result : " + result);
			logger.info("---------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all/{feedId}") 
	public ResponseEntity<List<LikeInfoVO>> readall(
			@PathVariable("feedId") int feedId) {
		// PathVariable("boardId") : /all/{boardId} 값을 설정된 변수에 저장
		// 실제로 할 때는 /all/1 -> 이런식으로 한당 ㅎㅅㅎ
		logger.info("★ LikeRESTController 전체검색 : " + feedId);
		
		List<LikeInfoVO> list = likeInfoService.read_all(feedId);
		return new ResponseEntity<List<LikeInfoVO>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/{likeId}") // DELETE
	public ResponseEntity<Integer> deleteLike(
			@PathVariable("likeId") int likeId) {
		logger.info("likeId = " + likeId);
	
		LikeInfoVO likeinfovo = likeInfoService.read(likeId);
		logger.info("★ LikeRESTController 찾은 좋아요 번호 : " + likeinfovo);
		
		int result = 0; // 예외처리
		try {
			result = likeInfoService.delete(likeId);
			logger.info("result : " + result);
			logger.info("---------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}// ReplyRESTcontroller
