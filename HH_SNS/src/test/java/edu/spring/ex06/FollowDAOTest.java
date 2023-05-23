package edu.spring.ex06;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.ex06.persistence.FollowDAO;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class FollowDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(FollowDAOTest.class);

	

	
	@Autowired
	private FollowDAO followDao;


	@Test
	public void testDAO() {
		followTestInsert();
		//followerTestSelect();
		//followingTestSelect();
		//followingTestDelete();
	}// end testDAO()

	private void followingTestDelete() {
		String followerUserId = "asd";
		String followingUserId="a";
		
		int result = followDao.delete(followerUserId, followingUserId);
		if(result == 1) {
			logger.info("삭제 성공");
		}else {
			logger.info("삭제 실패");
		}
		
	}

	private void followingTestSelect() {
		String followingUserId="b";
		
		int count = followDao.selectFollowing(followingUserId);
		logger.info("팔로잉 수 : " + count);
		
	}

	private void followerTestSelect() {
		String followerUserId="a";
		
		int count = followDao.selectFollower(followerUserId);
		logger.info("팔로워 수 : " + count );
		
	}

	

	// --------------------------------------------------
	private void followTestInsert() {
		String followerUserId="asd";
		String followingUserId="as";
		
		int result = followDao.insert(followerUserId, followingUserId);
		if(result == 1) {
			logger.info("인서트 성공");
		}else {
			logger.info("인서트 실패");
		}
	}
	
	


}
