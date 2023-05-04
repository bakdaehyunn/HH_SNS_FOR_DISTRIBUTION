package edu.spring.ex06.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex06.domain.UserInfoVO;
@Repository
public class UserInfoDAOImple implements UserInfoDAO{
	private static final Logger logger =
			LoggerFactory.getLogger(UserInfoDAOImple.class);
	private static final String NAMESPACE = "edu.spring.ex06.UserinfoMapper";
			
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(UserInfoVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public UserInfoVO select(String userId) {
		logger.info("select() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_all_by_user_id", userId);
	}

	@Override
	public int update(UserInfoVO vo) {
		logger.info("update() 호출");
		return sqlSession.update(NAMESPACE + ".update_userinfo", vo);
	}
	
	@Override
	public int updateProfile(UserInfoVO vo) {
		logger.info("updateProfile() 호출");
		return sqlSession.update(NAMESPACE + ".update_profile", vo);
	}
	

	@Override
	public int delete(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.delete(NAMESPACE + ".delete", userId);
	}

	@Override
	public int select(String userId, String userPassword) {
		logger.info("select() 호출");
		logger.info("userId = " + userId + ", password = " + userPassword);
		Map<String, Object> args = new HashMap();
		args.put("userId", userId);
		args.put("userPassword", userPassword);
		return sqlSession.selectOne(NAMESPACE + ".select_check_user_acc", args) ;
		
	}

	

}
