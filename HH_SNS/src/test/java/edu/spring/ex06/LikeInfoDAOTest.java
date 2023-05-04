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
		testInsert();
//		testDelete();
	}// end testDAO()
	
//	--------------------------------------------------

	private void testInsert() {
		String userid = "asss"; // 실제 데이터베이스에 존재하는 유저아이디 입력
		UserInfoVO uservo = userdao.select(userid);

		// db에 아이디 있는지 체크
		if (uservo == null) {
			logger.info("일치하는 ID가 없습니다.");
			return;
		}
		
		int feedid = 10; // 실제 데이터베이스에 존재하는 피드번호 입력
		FeedVO feedvo = feeddao.select(feedid);
		
		if (feedvo == null) {
			logger.info("ID와 일치하는 피드 번호가 없습니다.");
			return;
		}
		
		
		LikeInfoVO vo = new LikeInfoVO(0, uservo.getUserId(), feedvo.getFeedId(), 0, 0);
		int result = likedao.insert(vo);
		logger.info("♠ 결과 : " + result + "행 삽입");

	}// end testInsert

//	--------------------------------------------------

	private void testDelete() {
		String userid = "asss";
		UserInfoVO uservo = userdao.select(userid);

		if (uservo == null) {
			logger.info("일치하는 ID가 없습니다.");
			return;
		}
		
		int feedid = 10; // 실제 데이터베이스에 존재하는 피드번호 입력
		FeedVO feedvo = feeddao.select(feedid);
		
		if (feedvo == null) {
			logger.info("ID와 일치하는 피드 번호가 없습니다.");
			return;
		}

		LikeInfoVO vo = new LikeInfoVO(feedvo.getFeedId(), null, 0, 0, 0);
		int result = likedao.delete(feedvo.getFeedId());

		if (result == 1) {
			logger.info("♠ 삭제 성공");
		}

	}// end testDelete

//	--------------------------------------------------

}
