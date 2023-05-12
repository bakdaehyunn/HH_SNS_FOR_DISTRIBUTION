package edu.spring.ex06;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.FeedDAO;
import edu.spring.ex06.persistence.FollowerDAO;
import edu.spring.ex06.persistence.LikeInfoDAO;
import edu.spring.ex06.persistence.UserInfoDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class FollowDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(FollowDAOTest.class);

	private static final String NAMESPACE = "edu.spring.ex06.FeedMapper";

	@Autowired
	private FollowerDAO followDao;


	@Test
	public void testDAO() {
		testInsert();
	}// end testDAO()

	// --------------------------------------------------
	private void testInsert() {
	}// end testInsert


}
