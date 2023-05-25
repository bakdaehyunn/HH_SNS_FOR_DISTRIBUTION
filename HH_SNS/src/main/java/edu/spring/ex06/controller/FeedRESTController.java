package edu.spring.ex06.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.service.FeedService;
import edu.spring.ex06.service.LikeInfoService;
import edu.spring.ex06.service.UserInfoService;
import edu.spring.ex06.util.MediaUtil;

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
	
	@Autowired
	private LikeInfoService likeInfoService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;

	@PostMapping
	public ResponseEntity<Integer> createFeed(FeedVO feedvo, MultipartFile feedPicture,  HttpSession session) {
		// @RequestBody
		// - 클라이언트에서 전송받은 json 데이터를
		// 자바 객체로 변환해주는 annotation
		logger.info("★ FeedRESTController : " + feedvo.toString());

		String userId = (String) session.getAttribute("userId");
		// 세션에 저장된 아이디가 유저정보에서 있는지 확인을 하고(db)
		// 있으면 현재 세션 아이디와 유저정보와 일치하는 유저의 닉네임과 프로필을 가져온다
		
		if(userId != null && userId.contains(feedvo.getUserId())) {
			// 현재 세션 아이디와 유저정보와 일치하는 유저의 닉네임과 프로필 가져오는 코드 작성
			logger.info("현재 세션 아이디 : " + userId);
			logger.info("포함되어 있는 유저 정보 : " + userId.contains(feedvo.getUserId()));
			
			UserInfoVO userinfovo = userInfoService.read(userId);
			
			String userNickname = userinfovo.getUserNickname();
			String userProfile = userinfovo.getUserProfile();
			String musicTitle = "X";
	
			if (feedPicture != null && feedPicture.getSize() > 0) {
			    // 파일이 업로드된 경우
			    logger.info("파일 사이즈 : " + feedPicture.getSize());
			    String savedFileName = saveUploadFile(feedPicture);
			    feedvo.setFeedPhoto(savedFileName);
			    logger.info("file 추가");
				logger.info("feedvo.getPhoto : " + feedvo.getFeedPhoto());
			} else {
			    // 파일이 업로드되지 않은 경우
			    feedvo.setFeedPhoto("null");
			}

			// feedContent 값은 항상 유지
//			String feedContent = feedvo.getFeedContent();
//			feedvo.setFeedContent(feedContent);
			feedvo = new FeedVO(0, feedvo.getFeedContent(), userId, userNickname, userProfile, 0, 0, null, musicTitle, feedvo.getFeedPhoto());
			

			logger.info("★ 등록할 정보 : " + feedvo.toString());
			
		} else {
			// 유저정보가 없는 경우나 세션이 만료된 경우 등에 대한 예외 처리
			logger.info("없음 !");
		}

		int result = 0; // 예외처리
		try {
			result = feedService.create(feedvo);
			logger.info("result : " + result);
			logger.info("---------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Integer>(result, HttpStatus.OK);

	}


	private String saveUploadFile(MultipartFile feedPicture) {
		// UUID : 업로드하는 파일 이름이 중복되지 않도록 값 생성
		UUID uuid = UUID.randomUUID();
		String savedName = uuid + "_" + feedPicture.getOriginalFilename();
		File target = new File(uploadPath, savedName);
		try {
			FileCopyUtils.copy(feedPicture.getBytes(), target);
			logger.info("파일 저장 성공");
			return savedName;
		} catch (IOException e) {
			logger.info("파일 저장 실패");
			return null;
		}
		
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


	@GetMapping("/all/{feedId}") 
	public ResponseEntity<List<FeedVO>> readFeeds(
			@PathVariable("feedId") int feedId) {
		// PathVariable("boardId") : /all/{boardId} 값을 설정된 변수에 저장
		// 실제로 할 때는 /all/1 -> 이런식으로 한당 ㅎㅅㅎ
		logger.info("★ FeedRESTController 전체검색 : " + feedId);
		
		List<FeedVO> list = feedService.readAll();
		return new ResponseEntity<List<FeedVO>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/allbyId/{userId}")
	public ResponseEntity<List<FeedVO>> readFeedsById(
			@PathVariable("userId") String userId) {
		logger.info("★ FeedRESTController 아이디 전체검색 : " + userId);
		
		List<FeedVO> list = feedService.readAllbyId(userId);
		logger.info("♥ 아이디 기준 총 정보 : " + list.size());
		return new ResponseEntity<List<FeedVO>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/{feedId}") // PUT : 댓글 수정
	public ResponseEntity<Integer> updateFeeds(
			@PathVariable("feedId") int feedId,
			@RequestBody String feedContent
			// front에서 json으로 데이터를 가져온다는뜻
			){
		int result = feedService.update_content(feedId, feedContent);
		logger.info("---------------------------------------------------------------");
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{feedId}") // DELETE
	public ResponseEntity<Integer> deleteFeeds(
			@PathVariable("feedId") int feedId) {
		logger.info("feedId = " + feedId);
		
		int result = 0; // 예외처리
		try {
			result = feedService.delete(feedId);
			logger.info("---------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}// ReplyRESTcontroller
