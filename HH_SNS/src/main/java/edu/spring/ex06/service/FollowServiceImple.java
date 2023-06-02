package edu.spring.ex06.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.FollowDAO;
import edu.spring.ex06.persistence.NotiDAO;


@Service
public class FollowServiceImple implements FollowService {
	private static Logger logger = 
			LoggerFactory.getLogger(FollowServiceImple.class);

	@Autowired
	private FollowDAO followdao;
	
	@Autowired
	private NotiDAO notiDAO;
	
	@Transactional(value= "transactionManager")
	@Override
	public int create(String followerUserId, String followingUserId) throws Exception{
		logger.info("create() 호출 ");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followingUserId" + followingUserId);
		followdao.insert(followerUserId, followingUserId);
		notiDAO.insert(followerUserId, followingUserId, "follow");
		return 1;
	}

	@Override
	public int readFollowerCnt(String followerUserId) {
		logger.info("readFollower() 호출 : followerUserId = " + followerUserId);
		return followdao.selectFollowerCnt(followerUserId);
	}
	
	public int readFollowingCnt(String followingUserId) {
		logger.info("readFollowing() 호출 : followingUserId = " + followingUserId);
		return followdao.selectFollowingCnt(followingUserId);
	}
	
	@Transactional(value= "transactionManager")
	@Override
	public int delete(String followerUserId, String followingUserId) throws Exception{
		logger.info("delete() 호출 ");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followerUserId : " + followingUserId );
		followdao.delete(followerUserId, followingUserId);
		notiDAO.delete(followerUserId, followingUserId);
		return 1;
	}
	
	@Override
	public List<UserInfoVO> readFollowerList(String followerUserId) {
		logger.info("readFollowerList() 호출");
		logger.info("followerUserId : " + followerUserId);
		return followdao.selectFollowerList(followerUserId);
	}

	@Override
	public List<UserInfoVO> readFollowingList(String followingUserId) {
		logger.info("readFollowingList() 호출");
		logger.info("followingUserId : " + followingUserId);
		return followdao.selectFollowingList(followingUserId);
	}

	@Override
	public int readFollowingCheck(String followerUserId, String followingUserId) {
		logger.info("readFollowingCheck() 호출");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followingUserId : " + followingUserId);
		return followdao.selectFollowingCheck(followerUserId, followingUserId);
	}

	

}
