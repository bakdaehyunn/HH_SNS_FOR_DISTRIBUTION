package edu.spring.ex06.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.LikeInfoVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.service.FeedService;
import edu.spring.ex06.service.LikeInfoService;
import edu.spring.ex06.service.ReplyService;
import edu.spring.ex06.service.UserInfoService;
import edu.spring.ex06.util.MediaUtil;

@Controller
@RequestMapping(value = "/feed")
public class FeedController {
	private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

	@Autowired
	private FeedService feedService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private LikeInfoService likeInfoService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	
	@GetMapping("/reply")
	public void replyGet() {
		logger.info("replyGET() 호출");
	}

//	------------------------------------------------------------------
	@GetMapping("/main")
	public void mainGET(Model model, HttpServletRequest request) {
		logger.info("★ FeedControllerGET 호출");

		// 로그인 필요 X
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") != null) {
			String sessionuserId = (String) session.getAttribute("userId");
			UserInfoVO userinfovo = userInfoService.read(sessionuserId);
			model.addAttribute("userinfovo", userinfovo);
		}
		
		FeedVO feedvo = new FeedVO();
		int likeCount = feedvo.getLikeCount();
		model.addAttribute("likeCount", likeCount);

	}

	@GetMapping("/mylist")
	public void list(Model model, String userId, HttpServletRequest request) {
		logger.info("★ FeedController mylist 호출");

		// 로그인 필요 X
		HttpSession session = request.getSession();
		// 세션 아이디값 O
		if (session.getAttribute("userId") != null) {
			String sessionuserId = (String) session.getAttribute("userId");
			UserInfoVO sessionUserinfovo = userInfoService.read(sessionuserId);
			model.addAttribute("sessionUserinfovo", sessionUserinfovo);
		} 
		
		UserInfoVO userinfovo = userInfoService.read(userId);
		model.addAttribute("userinfovo", userinfovo);

		List<FeedVO> list = feedService.readAllbyId(userId);
		logger.info("★ List feedvo 정보 : " + list.toString());

		for (FeedVO feedvo : list) {
			model.addAttribute("feedvo", feedvo);
		}
	}

	@GetMapping("/detail")
	public void detail(Model model, @ModelAttribute(name = "feedId") int feedId, FeedVO feedvo, LikeInfoVO likeinfovo,
			HttpServletRequest request) {
		logger.info("★ FeedController detail 호출");

		// 로그인 필요 X
		HttpSession session = request.getSession();
		// 세션ID가 있을때 = 로그인했을때
		if (session.getAttribute("userId") != null) {
			String sessionuserId = (String) session.getAttribute("userId");
			UserInfoVO userinfovo = userInfoService.read(sessionuserId);
			model.addAttribute("userinfovo", userinfovo);
			logger.info("세션 아이디 : " + userinfovo);
		}

		feedvo = feedService.read(feedId);
		model.addAttribute("feedvo", feedvo);
		logger.info("피드 정보 : " + feedvo);
		
		List<LikeInfoVO> list = likeInfoService.read_all(feedId);
		for(LikeInfoVO likevo : list) {
			logger.info(likevo.toString());
			model.addAttribute("likevo", likevo);
		}
		
		
	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("★ FeedController display 호출");

		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		String filePath = uploadPath + "\\" + fileName;
		logger.info("★" + filePath);

		try {
			in = new FileInputStream(filePath);

			// 파일 확장자
			String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);

			// 응답 해더(response header)에 Content-Type 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));

			// 데이터 전송
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), // 파일에서 읽은 데이터
					httpHeaders, // 응답 해더
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;

	}

}
