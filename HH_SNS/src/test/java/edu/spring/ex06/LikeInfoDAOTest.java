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
		testInsert_feed();
//		testInsert_reply();
//		testInsert_comment();
//		testDelete();
	}// end testDAO()

//	--------------------------------------------------

	private void testInsert_feed() {
		String userid = "asss"; // 실제 데이터베이스에 존재하는 유저아이디 입력
		UserInfoVO uservo = userdao.select(userid);

		// db에 아이디 있는지 체크
		if (uservo == null) {
			logger.info("일치하는 ID가 없습니다.");
			return;
		} else {
			logger.info("현재 ID : " + uservo.getUserId());
		}

		int feedid = 10; // 실제 데이터베이스에 존재하는 피드번호 입력
		FeedVO feedvo = feeddao.select(feedid);
		if (feedvo == null) {
			logger.info("ID와 일치하는 피드 번호가 없습니다.");
			return;
		} else {
			logger.info("현재 피드 번호 : " + feedvo.getFeedId());
		}

		logger.info("★ ID vo : " + uservo);
		logger.info("★ 현재 ID : " + uservo.getUserId());
		logger.info("★ 피드 vo : " + feedvo);
		logger.info("★ 현재 피드에 존재하는 ID : " + feedvo.getUserId());
		logger.info("★ 현재 피드 번호 : " + feedvo.getFeedId());

		// 유저id랑 피드 번호가 피드VO에 존재 할 시에
		// 피드vo에 있는 피드 작성 번호에 유저 아이디와 유저vo에 있는 유저 아이디가 일치하고
		// 피드vo에 있는 피드 번호가 현재 넣을 피드 아이디가 같은지 확인
		if (feedvo.getUserId().equals(uservo.getUserId()) && feedvo.getFeedId() == feedid) {
			LikeInfoVO likevo = new LikeInfoVO(0, uservo.getUserId(), feedvo.getFeedId(), 0, 0);
			int result = likedao.insert(likevo);
			logger.info("♠ 결과 : " + result + " 좋아요 등록");
			
		} else {
			logger.info("♠ 현재 ID : " + uservo.getUserId() + ", 피드 번호 : " + feedvo.getFeedId() + "가 존재하지 않습니다.");
		}

	}// end testInsert

//	--------------------------------------------------

	private void testInsert_reply() {
		// (수정 전 ★)
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

	}

//--------------------------------------------------

	private void testInsert_comment() {
		// (수정 전 ★)
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

	}

//	--------------------------------------------------

	private void testDelete() {
		String userid = "asss";
		UserInfoVO uservo = userdao.select(userid);

		if (uservo == null) {
			logger.info("일치하는 ID가 없습니다.");
			return;
		} else {
			logger.info("현재 ID : " + uservo.getUserId());
		}

		int feedid = 10;
		FeedVO feedvo = feeddao.select(feedid);
		if (feedvo == null) {
			logger.info("ID와 일치하는 피드 번호가 없습니다.");
			return;
		} else {
			logger.info("현재 피드 번호 : " + feedvo.getFeedId());
		}

		if (feedvo.getUserId().equals(uservo.getUserId()) && feedvo.getFeedId() == feedid) {
			LikeInfoVO likevo = new LikeInfoVO(12, null, 0, 0, 0);
			int result = likedao.delete(likevo.getLikeId());

			if (result == 1) {
				logger.info("♠ 삭제 성공");
			}

		} else {
			logger.info("♠ 현재 ID : " + uservo.getUserId() + ", 피드 번호 : " + feedvo.getFeedId() + "가 존재하지 않습니다.");
		}

	}// end testDelete

//	--------------------------------------------------

}
