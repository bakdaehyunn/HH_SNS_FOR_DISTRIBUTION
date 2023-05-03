package edu.spring.ex06;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.UserInfoDAO;
import oracle.jdbc.OracleDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class UserInfoDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(UserInfoDAOTest.class);
	private static final String NAMESPACE = "edu.spring.ex06.UserinfoMapper";

	@Autowired
	private UserInfoDAO dao;

	@Test
	public void testDAO() {
		//testInsert();
		//testSelect();
		//testUpdate();
		//testProfileUpdate();
		//testlogin();
		testDelete();
		
		
	}

	private void testDelete() {
		String userId= "asd";
		int result = dao.delete(userId);
		if(result == 1) {
			logger.info("delete 성공");
		} else {
			logger.info("delete 실패");
		}
	}

	private void testProfileUpdate() {
		UserInfoVO vo = new UserInfoVO();
		vo.setUserNickname("asss");
		vo.setUserProfile("asdsad");
		vo.setUserId("asd"); 
		int result = dao.updateProfile(vo);
		if(result == 1) {
			logger.info("update 성공");
		} else { 
			logger.info("update 실패");
		}
		
	}

	private void testUpdate() {
		Date s = new Date(2002, 8, 13);
		UserInfoVO vo = new UserInfoVO("asd", "123", "asdsad", null, s, "sdfs@asdfsd.com",null); 
		int result = dao.update(vo);
		if(result == 1 ) {
			logger.info("update 성공");
		} else {
			logger.info("update 실패");
		}
	}

	private void testInsert() {
		Date s = new Date(2002, 8, 13);
		UserInfoVO vo = new UserInfoVO("asd", "123", "asdsad", "2000", s, "sdfs@asdfsd.com","22");
		int result = dao.insert(vo);
		if( result == 1) {
			logger.info("insert 성공");
		} else {
			logger.info("insert 실패");
			
		}
	}
	
	private void testSelect() {
		
		UserInfoVO vo = dao.select("asd");
		logger.info(vo.toString());
		
	}
	private void testlogin() {
		String userId="asd";
		String userPassword="123";
		int result = dao.select(userId, userPassword);
		logger.info(String.valueOf(result));
	}
	
	

	

	

} // end SqlSessionTest
