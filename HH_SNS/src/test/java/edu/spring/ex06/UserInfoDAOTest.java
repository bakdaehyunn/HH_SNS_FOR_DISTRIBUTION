package edu.spring.ex06;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

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
		testInsert();
		//testSelect();
		
		
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

	

	

} // end SqlSessionTest
