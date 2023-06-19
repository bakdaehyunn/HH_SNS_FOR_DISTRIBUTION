package edu.spring.ex06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.CommentInfoDAO;
import edu.spring.ex06.persistence.FeedDAO;
import edu.spring.ex06.persistence.FollowDAO;
import edu.spring.ex06.persistence.NotiDAO;
import edu.spring.ex06.persistence.ReplyDAO;
import edu.spring.ex06.persistence.UserInfoDAO;

@Service
public class UserInfoServiceImple implements UserInfoService {
	private static Logger logger =
			LoggerFactory.getLogger(UserInfoServiceImple.class);
	
	@Autowired
	private UserInfoDAO dao;
	
	@Autowired
	private FeedDAO feedDAO;
	
	@Autowired
	private ReplyDAO replyDAO;
	
	@Autowired
	private NotiDAO notiDAO;
	
	@Autowired
	private FollowDAO followDAO;
	
	@Autowired
	private CommentInfoDAO commentDAO;
	
	@Override
	public int create(UserInfoVO vo) {
		logger.info("create() 호출 : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public UserInfoVO read(String userId) {
		logger.info("read() 호출 : userId = " + userId);
		return dao.select(userId);
	}

	@Override
	public int read(String userId, String UserPassword) {
		logger.info("read() 호출 ");
		logger.info("userId = " + userId);
		logger.info("UserPassword = " + UserPassword);
		return dao.select(userId, UserPassword);
	}

	@Override
	public int update(UserInfoVO vo) {
		logger.info("read() 호출 : vo = " + vo.toString());
		return dao.update(vo);
	}
	
	@Transactional(value= "transactionManager")
	@Override
	public int updateProfile(UserInfoVO vo) throws Exception{
		logger.info("updateProfile() 호출 : vo = " + vo.toString());
		dao.updateProfile(vo);
		logger.info("update_feedprofile()");
		String userNicknamne = vo.getUserNickname();
		String userProfile = vo.getUserProfile();
		String userId = vo.getUserId();
		feedDAO.update_profile(userNicknamne, userProfile, userId);
		replyDAO.update_profile(userNicknamne, userProfile, userId);
		commentDAO.update_profile(userNicknamne, userProfile, userId);
		return 1;
	}
	@Transactional(value= "transactionManager")
	@Override
	public int delete(String userId) throws Exception{
		logger.info("delete() 호출 : userId = " + userId);
		dao.delete(userId);
		feedDAO.deleteUserId(userId);
		notiDAO.deleteReceiverId(userId);
		followDAO.deleteFollower(userId);
		followDAO.deleteFollowing(userId);
		return 1;
	}

	@Override
	public int readUserId(String userId) {
		logger.info("readUserId() 호출 : userId = " + userId);
		return dao.selectUserId(userId);
	}

	@Override
	public int readUserEmail(String userEmail) {
		logger.info("readUserEmail() 호출 : userEmail = " + userEmail);
		return dao.selectUserEmail(userEmail);
	}

}
