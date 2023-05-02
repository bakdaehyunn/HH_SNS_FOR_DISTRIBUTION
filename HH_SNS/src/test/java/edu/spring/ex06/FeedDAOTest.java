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
import edu.spring.ex06.persistence.FeedDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class FeedDAOTest {
	private static final Logger logger =
			LoggerFactory.getLogger(FeedDAOTest.class);

	private static final String NAMESPACE =
			"edu.spring.ex06.FeedMapper";
	
	@Autowired
	private FeedDAO dao;
	
	@Test
	public void testDAO() {
		testInsert();
//		testSelectAll();
//		testUpdate();
//		testDelete();
	}// end testDAO()
	
	/*
	 * private int feedId;
	   private String feedContent;
	   private String userId;
	   private String userNickname;
	   private String userProfile;
	   private int replyCount;
	   private int likeCount;
	   private Date feedDate;
	   private String musicTitle;
	 * 
	 * */

//	--------------------------------------------------
	private void testInsert() {
		FeedVO vo = new FeedVO(0, "피드내용", "유저아이디", "유저닉네임", "프로필사진", 0, 0, null, "음악제목");
		int result = dao.insert(NAMESPACE, vo);
		logger.info(result + "행 삽입");
	}// end testInsert

}
