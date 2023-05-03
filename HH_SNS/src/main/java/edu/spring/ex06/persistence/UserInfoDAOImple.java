package edu.spring.ex06.persistence;

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
	public UserInfoVO select(String userid) {
		logger.info("select() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_all_by_user_id", userid);
	}

	@Override
	public int update(UserInfoVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
