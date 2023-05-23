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

import edu.spring.ex06.domain.ReplyVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.ReplyDAO;
import edu.spring.ex06.persistence.UserInfoDAO;
import oracle.jdbc.OracleDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class replyDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(replyDAOTest.class);
	

	@Autowired
	private ReplyDAO dao;

	@Test
	public void testDAO() {
		
		//testidcheck();
		//testinsert();
		//testselect();
		//testupdate();
		testdelete();
	}
	

	

	private void testdelete() {
		dao.delete(14);
		
	}




	private void testupdate() {
		dao.update(14, "asdsad");
		
	}




	private void testselect() {
		List<ReplyVO> list = dao.select(1);
		logger.info(list.toString());
		
	}




	private void testinsert() {
		ReplyVO vo = new ReplyVO(0, 1,"a" , "a", "x", "asd", null, 0, 0);
		int result = dao.insert(vo);
		if(result == 1) {
			logger.info("댓글 삽입 성공");
		}
		else {
			logger.info("댓글 삽입 실패");
		}
		
	}




	private void testidcheck() {
//		String userId="as";
//		//int result = dao.selectUserId(userId);
//		if(result == 1) {
//			logger.info("아이디가 이미 존재합니다.");
//		} else {
//			logger.info("사용가능한 아이디 입니다.");
//		}
		
	}


	

	

	

} // end SqlSessionTest
