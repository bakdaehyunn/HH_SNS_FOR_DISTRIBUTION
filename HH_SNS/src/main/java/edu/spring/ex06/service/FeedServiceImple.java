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
	public List<FeedVO> readAll(int feedId) {
		logger.info("★ FeedService 검색 : " + feedId);
		return feeddao.selectAll(feedId);
	}
	
	@Override
	public FeedVO read(int feedId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(int feedId, FeedVO vo) {
		logger.info("★ FeedService 수정 : " + vo.toString());
		return feeddao.update(feedId, vo);
	}

	@Override
	public int delete(int feedId) {
		logger.info("★ FeedService 삭제 : " + feedId);
		return feeddao.delete(feedId);
	}

	@Override
	public int getTotalLikes() {
		logger.info("★ FeedService 좋아요 수");
		return feeddao.getTotalLike();
	}
	
	@Override
	public int getTotalComments() {
		// TODO Auto-generated method stub
		return 0;
	}

	


}
