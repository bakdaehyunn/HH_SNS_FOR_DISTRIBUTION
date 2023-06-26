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
	public int insert(UserInfoVO vo) { // 회원정보 생성(회원가입)
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public UserInfoVO select(String userId) { // 회원 정보 불러오기
		logger.info("select() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_all_by_user_id", userId);
	}
	
	@Override
	public int select(String userId, String userPassword) { // 로그인(회원정보 확인)
		logger.info("select() 호출");
		logger.info("userId = " + userId + ", password = " + userPassword);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("userId", userId);
		args.put("userPassword", userPassword);
		return sqlSession.selectOne(NAMESPACE + ".select_check_user_acc", args) ;
		
	}

	@Override
	public int selectUserId(String userId) { // 아이디 중복체크
		logger.info("selectUserId() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_check_user_id", userId);
	}

	@Override
	public int selectUserEmail(String userEmail) { // 이메일 중복체크
		logger.info("selectUserEmail() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_check_user_email", userEmail);
	}

	@Override
	public int update(UserInfoVO vo) { // 회원정보 수정
		logger.info("update() 호출");
		return sqlSession.update(NAMESPACE + ".update_userinfo", vo);
	}
	
	@Override
	public int updateProfile(UserInfoVO vo) { // 프로필정보 수정
		logger.info("updateProfile() 호출");
		return sqlSession.update(NAMESPACE + ".update_profile", vo);
	}
	

	@Override
	public int delete(String userId) { // 회원 탈퇴
		// TODO Auto-generated method stub
		return sqlSession.delete(NAMESPACE + ".delete", userId);
	}

}
