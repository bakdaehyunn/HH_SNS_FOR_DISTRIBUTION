package edu.spring.ex06;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.LikeInfoVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.FeedDAO;
import edu.spring.ex06.persistence.LikeInfoDAO;
import edu.spring.ex06.persistence.UserInfoDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class LikeInfoDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(LikeInfoDAOTest.class);

	private static final String NAMESPACE = "edu.spring.ex06.LikeInfoMapper";

	@Autowired
	private LikeInfoDAO likedao;

	@Autowired
	private FeedDAO feeddao;

	@Autowired
	private UserInfoDAO userdao;

	@Test
	public void testDAO() {
//		testInsert_feed();
//		test_select();
		test_duplication_Id();
//		testInsert_comment();
//		testDelete();
	}// end testDAO()

	private void test_duplication_Id() {
		String userId = "ss";
		
		int check = likedao.select_check(userId);
		
		if(check == 1) {
			logger.info("존재하는 아이디");
		} else {
			logger.info("없는 아이디");
		}
		
		
	}

//	--------------------------------------------------

	private void testInsert_feed() {
		

	}// end testInsert

//	--------------------------------------------------

	private void test_select() {
	}

//--------------------------------------------------

	private void testInsert_comment() {
		

	}

//	--------------------------------------------------

	private void testDelete() {


	}// end testDelete

//	--------------------------------------------------

}
