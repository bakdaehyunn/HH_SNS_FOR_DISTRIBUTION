package edu.spring.ex06.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.FeedDAO;

@Service
public class FeedServiceImple implements FeedService{
	private static final Logger logger =
			LoggerFactory.getLogger(FeedServiceImple.class);
	
	@Autowired
	private FeedDAO feeddao;

	@Override
	public int create(FeedVO feedvo, UserInfoVO userinfovo, HttpSession session) {
		String userid = (String) session.getAttribute("userId");
		
		String feedcontent = feedvo.getFeedContent();
		String usernickname = userinfovo.getUserNickname();
		String userprofile = userinfovo.getUserProfile();
		Date feeddate = new Date();
		String musictitle = "X";
		
		feedvo = new FeedVO(0, feedcontent, userid, usernickname, userprofile, 0, 0, feeddate, musictitle);
		logger.info("★ FeedServiceImple 등록 : " + feedvo.toString());
		// FeedVO [feedId=0, feedContent=ㅁㄴㅇ, userId=asss, userNickname=null, userProfile=null, replyCount=0, likeCount=0, feedDate=null, musicTitle=null]
		
		return feeddao.insert(feedvo);
	}// end create()

	@Override
	public List<FeedVO> readAll() {
		logger.info("★ FeedServiceImple 전체 검색 ");
		return feeddao.selectAll();
	}
	
	@Override
	public List<FeedVO> readAllbyId(String userId) {
		logger.info("★ FeedServiceImple 아이디 전체 검색 ");
		return feeddao.selectAllbyId(userId);
	}
	
	@Override
	public FeedVO read(int feedId) {
		logger.info("★ FeedServiceImple 검색 : 피드 번호 = " + feedId);
		return feeddao.select(feedId);
	}
	
	@Override
	public FeedVO read(int feedId, String userId) {
		logger.info("★ FeedServiceImple 검색 : 피드 번호 = " + feedId + ", 로그인한 유저 아이디 = " + userId);
		return feeddao.select(feedId, userId);
	}
	
	@Override
	public FeedVO read(String userId) {
		logger.info("★ FeedServiceImple 검색 : 유저 아이디 = " + userId);
		return feeddao.select(userId);
	}
	@Override
	public int update(int feedId, String feedContent) {
		logger.info("★ FeedServiceImple 수정");
		logger.info("feedId = " + feedId + " feedContent = " + feedContent);
		return feeddao.update(feedId, feedContent);
	}

	@Override
	public int delete(int feedId) {
		logger.info("★ FeedServiceImple 삭제 : " + feedId);
		return feeddao.delete(feedId);
	}

	@Override
	public int getTotalLikes() {
		logger.info("★ FeedServiceImple 좋아요 수");
		return feeddao.getTotalLike();
	}
	
	@Override
	public int getTotalComments() {
		// TODO Auto-generated method stub
		return 0;
	}


}
