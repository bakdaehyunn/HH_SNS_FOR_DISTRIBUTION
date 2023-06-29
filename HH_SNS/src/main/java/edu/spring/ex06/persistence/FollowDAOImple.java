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
import edu.spring.ex06.util.PageCriteria;

@Repository
public class FollowDAOImple implements FollowDAO{
	private static final Logger logger =
			LoggerFactory.getLogger(FollowDAOImple.class);
	private static final String NAMESPACE =  "edu.spring.ex06.FollowMapper"; //팔로우 매퍼 경로
	
	@Autowired// SqlSession 주입
	private SqlSession sqlSession;

	@Override
	public int insert(String followerUserId, String followingUserId) { // 팔로우 등록
		logger.info("insert() 호출");
		logger.info("followerUserId = " + followerUserId + ", followerUserId = " + followerUserId);
		Map<String, Object> args = new HashMap();
		args.put("followerUserId", followerUserId);
		args.put("followingUserId", followingUserId);
		return sqlSession.insert(NAMESPACE + ".insert", args);
	}

	@Override
	public int selectFollowerCnt(String followerUserId) { // 팔로워 수 불러오기
		logger.info("selectFollowerCnt() 호출");
		logger.info("followerUserId : " + followerUserId);
		return sqlSession.selectOne(NAMESPACE +".select_follower_cnt", followerUserId);
	}
	
	@Override
	public int selectFollowingCnt(String followingUserId) { // 팔로잉 수 불러오기
		logger.info("selectFollowingCnt() 호출");
		logger.info("followingUserId : " + followingUserId);
		return sqlSession.selectOne(NAMESPACE +".select_following_cnt", followingUserId);
	}

	@Override
	public List<UserInfoVO> selectFollowingList(String userId) { // 팔로잉 리스트 불러오기
		logger.info("selectFollowingList() 호출");
		logger.info("userId :" + userId );
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("followerUserId", userId);
		return sqlSession.selectList(NAMESPACE + ".select_following_list", args);
	}

	@Override
	public List<UserInfoVO> selectFollowerList(String userId) { // 팔로워 리스트 불러오기
		logger.info("selectFollowerList() 호출");
		logger.info("UserId : " + userId);
		Map<String, Object> args = new HashMap<String, Object>();
		
		args.put("followingUserId", userId);
		return sqlSession.selectList(NAMESPACE + ".select_follower_list", args);
	}
	@Override
	public List<UserInfoVO> selectFollowerListPaging(String userId,PageCriteria criteria) {
		logger.info("selectFollowerListPaging() 호출 ");
		logger.info("followerUserId : "+userId);
		logger.info("start : " + criteria.getStart());
		logger.info("end : " + criteria.getEnd());
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("followingUserId", userId);
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		return sqlSession.selectList(NAMESPACE +".follower_list_paging", args);
	}

	@Override
	public int selectFollowingCheck(String followerUserId, String followingUserId) { // 팔로우 확인 
		logger.info("selectFollowingCheck() 호출");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followingUserId : " + followingUserId);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("followerUserId", followerUserId);
		args.put("followingUserId", followingUserId);
		return sqlSession.selectOne(NAMESPACE + ".select_following_check", args);
	}

	@Override
	public List<UserInfoVO> selectTagList( String followerUserId, String followingUserId) { // 태그할 친구 리스트 불러오기
		logger.info("selectHashtagList() 호출");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followingUserId : "+ followingUserId);
		Map<String, String> args = new HashMap<String, String>();
		args.put("followerUserId", followerUserId);
		args.put("followingUserId", followingUserId);
		return sqlSession.selectList(NAMESPACE + ".select_tag_list", args);
	}

	@Override
	public int delete(String followerUserId, String followingUserId) { // 팔로우 취소 
		logger.info("delete() 호출");
		logger.info("followerUserId : " + followerUserId + ", followingUserId : " + followingUserId);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("followerUserId", followerUserId);
		args.put("followingUserId", followingUserId);
		return sqlSession.delete(NAMESPACE + ".delete", args);
	}	
	
	@Override
	public int deleteFollow(String userId) { //유저아이디로 팔로우 내역 삭제
		logger.info("deleteFollower : " + userId);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("followerUserId", userId);
		args.put("followingUserId", userId);
		return sqlSession.delete(NAMESPACE + ".delete_follow", args);
	}

	

	
	
	

}
