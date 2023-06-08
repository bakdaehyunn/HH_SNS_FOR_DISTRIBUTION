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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Integer>createLike(@RequestBody Map<String,Object> param){
	//public ResponseEntity<Integer> createLike(@RequestBody LikeInfoVO likeinfovo, @RequestParam("feedUserId") String feedUserId) {
		// @RequestBody
		// - 클라이언트에서 전송받은 json 데이터를
		// 자바 객체로 변환해주는 annotation
		logger.info("★ LikeRESTController : " + param.toString());
		
		// 좋아요 db에 같은 피드 아이디 존재 X
		// 좋아요 db에 같은 유저 아이디 존재 X
		// 즉 이미 좋아요 누른 사람은 X
		String userId=(String) param.get("userId");
		int feedId = Integer.parseInt((String) param.get("feedId"));
		String feedUserId = (String) param.get("feedUserId");
		
		LikeInfoVO vo = new LikeInfoVO(0, userId,feedId, 0, 0, null);
		int result = 0; // 예외처리
		
		
		
		List<LikeInfoVO> list = likeInfoService.read_all(feedId);
		
		for(LikeInfoVO likevo : list) {
			logger.info("좋아요 정보 : " + likevo.toString());
		}
		
		try {
			result = likeInfoService.create(vo,feedUserId);
			logger.info("result : " + result);
			logger.info("---------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/check") 
	public ResponseEntity<Integer> read_check( 
			@RequestParam("userId") String userId,
	        @RequestParam("feedId") int feedId)  {
		
		logger.info("★ LikeRESTController 중복 검색 : " + userId  + ", "+ feedId);
		
		int result = likeInfoService.read_check(userId, feedId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/check/{userId}")
	public ResponseEntity<List<LikeInfoVO>> read_check_userId(
					@PathVariable("userId") String userId) {
		logger.info("★ LikeRESTController 유저아이디로 검색 : " + userId);
		
		List<LikeInfoVO> list = likeInfoService.read_check_all_id(userId);
		logger.info("♥ 아이디 기준 총 정보 : " + list.size());
		logger.info("---------------------------------------------------------------");
		return new ResponseEntity<List<LikeInfoVO>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/{likeId}") // DELETE
	public ResponseEntity<Integer> deleteLike(
			@PathVariable("likeId") int likeId) {
		logger.info("likeId = " + likeId);
		
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
