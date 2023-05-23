package edu.spring.ex06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		logger.info("read() 호출 : followerUserId = " + followerUserId);
		return dao.selectFollower(followerUserId);
	}
	
	public int readFollowing(String followingUserId) {
		logger.info("read() 호출 : followingUserId = " + followingUserId);
		return dao.selectFollowing(followingUserId);
	}
	

	@Override
	public int delete(String followerUserId, String followingUserId) {
		logger.info("delete() 호출 ");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followerUserId : " + followingUserId );
		return dao.delete(followerUserId, followingUserId);
	}

}
