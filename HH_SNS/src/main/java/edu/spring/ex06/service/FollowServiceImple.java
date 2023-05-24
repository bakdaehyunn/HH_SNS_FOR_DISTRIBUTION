package edu.spring.ex06.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.FollowDAO;


@Service
public class FollowServiceImple implements FollowService {
	private static Logger logger = 
			LoggerFactory.getLogger(FollowServiceImple.class);

	@Autowired
	private FollowDAO dao;
	
	@Override
	public int create(String followerUserId, String followingUserId) {
		logger.info("create() 호출 ");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followingUserId" + followingUserId);
		return dao.insert(followerUserId, followingUserId);
	}

	@Override
	public int readFollower(String followerUserId) {
		logger.info("readFollower() 호출 : followerUserId = " + followerUserId);
		return dao.selectFollowerCnt(followerUserId);
	}
	
	public int readFollowing(String followingUserId) {
		logger.info("readFollowing() 호출 : followingUserId = " + followingUserId);
		return dao.selectFollowingCnt(followingUserId);
	}
	

	@Override
	public int delete(String followerUserId, String followingUserId) {
		logger.info("delete() 호출 ");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followerUserId : " + followingUserId );
		return dao.delete(followerUserId, followingUserId);
	}
	
	@Override
	public List<UserInfoVO> readFollowerList(String followerUserId) {
		logger.info("readFollowerList() 호출");
		logger.info("followerUserId : " + followerUserId);
		return dao.selectFollowerList(followerUserId);
	}

	@Override
	public List<UserInfoVO> readFollowingList(String followingUserId) {
		logger.info("readFollowingList() 호출");
		logger.info("followingUserId : " + followingUserId);
		return dao.selectFollowingList(followingUserId);
	}

	

}
