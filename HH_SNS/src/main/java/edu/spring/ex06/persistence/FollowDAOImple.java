package edu.spring.ex06.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FollowDAOImple implements FollowDAO{
	private static final Logger logger =
			LoggerFactory.getLogger(FollowDAOImple.class);
	private static final String NAMESPACE =  "edu.spring.ex06.FollowMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(String followerUserId, String followingUserId) {
		logger.info("insert() 호출");
		logger.info("followerUserId = " + followerUserId + ", followerUserId = " + followerUserId);
		Map<String, Object> args = new HashMap();
		args.put("followerUserId", followerUserId);
		args.put("followingUserId", followingUserId);
		return sqlSession.insert(NAMESPACE + ".insert", args);
	}

	@Override
	public int selectFollower(String followerUserId) {
		logger.info("select() 호출");
		logger.info("followerUserId : " + followerUserId);
		return sqlSession.selectOne(NAMESPACE +".select_follower", followerUserId);
	}
	
	@Override
	public int selectFollowing(String followingUserId) {
		logger.info("select() 호출");
		logger.info("followingUserId : " + followingUserId);
		return sqlSession.selectOne(NAMESPACE +".select_following", followingUserId);
	}
	

	@Override
	public int delete(String followerUserId, String followingUserId) {
		logger.info("delete() 호출");
		logger.info("followerUserId : " + followerUserId + ", followingUserId : " + followingUserId);
		Map<String, Object> args = new HashMap();
		args.put("followerUserId", followerUserId);
		args.put("followingUserId", followingUserId);
		return sqlSession.delete(NAMESPACE + ".delete", args);
	}
	
	

}
