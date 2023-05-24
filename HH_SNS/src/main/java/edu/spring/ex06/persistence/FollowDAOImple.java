package edu.spring.ex06.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex06.domain.UserInfoVO;

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
	public int selectFollowerCnt(String followerUserId) {
		logger.info("selectFollowerCnt() 호출");
		logger.info("followerUserId : " + followerUserId);
		return sqlSession.selectOne(NAMESPACE +".select_follower_cnt", followerUserId);
	}
	
	@Override
	public int selectFollowingCnt(String followingUserId) {
		logger.info("selectFollowingCnt() 호출");
		logger.info("followingUserId : " + followingUserId);
		return sqlSession.selectOne(NAMESPACE +".select_following_cnt", followingUserId);
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

	@Override
	public List<UserInfoVO> selectFollowingList(String followingUserId) {
		logger.info("selectFollowingList() 호출");
		logger.info("followingUserId :" + followingUserId );
		return sqlSession.selectList(NAMESPACE + ".select_following_list", followingUserId);
	}

	@Override
	public List<UserInfoVO> selectFollowerList(String followerUserId) {
		logger.info("selectFollowerList() 호출");
		logger.info("followerUserId : " + followerUserId);
		return sqlSession.selectList(NAMESPACE + ".select_follower_list", followerUserId);
	}
	
	

}
