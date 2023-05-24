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
import edu.spring.ex06.persistence.LikeInfoDAO;
import edu.spring.ex06.persistence.UserInfoDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class FeedDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(FeedDAOTest.class);

	private static final String NAMESPACE = "edu.spring.ex06.FeedMapper";

	@Autowired
	private FeedDAO feeddao;

	@Autowired
	private UserInfoDAO userdao;

	@Test
	public void testDAO() {
//		testInsert();
		testSelectAll();
//		testSelect();
//		testUpdate();
//		testDelete();
//		testSelectAllbyId();
	}// end testDAO()

	// --------------------------------------------------
	private void testInsert() {
		String userid = "asss"; // 실제 데이터베이스에 존재하는 유저아이디 입력
		UserInfoVO uservo = userdao.select(userid);

		// db에 아이디 있는지 체크
		if (uservo == null) {
			logger.info("일치하는 ID가 없습니다.");
			return;
		}

		FeedVO feedvo = new FeedVO(0, "시간확인", uservo.getUserId(), uservo.getUserNickname(), uservo.getUserProfile(), 0, 0, null, "음악제목", null);

		int result = feeddao.insert(feedvo);
		logger.info("♠ 결과 : " + result + "행 삽입");

	}// end testInsert

//	--------------------------------------------------

	private void testSelectAll() {
		List<FeedVO> list = feeddao.selectAll();
		logger.info("총 개수 : " + list.size() + "개");
		for (FeedVO vo : list) {
			logger.info("♠ 결과 : " + vo.toString());
		}
	}// end testSelectAll

//	--------------------------------------------------

	private void testSelect() {
		FeedVO vo = feeddao.select(6);
		logger.info("♠ 결과 : " + vo.toString());
	}// end testSelect

//	--------------------------------------------------

	private void testUpdate() {
		String userid = "테스트";
		UserInfoVO uservo = userdao.select(userid);

		// db에 아이디 있는지 체크
		if (uservo == null) {
			logger.info("ID가 없습니다.");
			return;
		}

		// 피드번호 != 유저아이디
		FeedVO selectfeedid = feeddao.select(10);
		List<FeedVO> selectuserid = feeddao.selectAllbyId(uservo.getUserId());
		if (selectfeedid != selectuserid) {
			logger.info("♠ 피드번호 != 유저아이디");
			return;
		}
		
		int result = feeddao.update(10, "ㅎㅎ");

		if (result == 1) {
			logger.info("♠ 수정 성공");
		}

	}// end testUpdate

//	--------------------------------------------------

	private void testDelete() {
		String userid = "asss";
		UserInfoVO uservo = userdao.select(userid);

		if (uservo == null) {
			logger.info("일치하는 ID가 없습니다.");
			return;
		}


		FeedVO vo = new FeedVO(6, null, null, null, null, 0, 0, null, null,null);

		int result = feeddao.delete(11);

		if (result == 1) {
			logger.info("♠ 삭제 성공");
		}

	}// end testDelete

//	--------------------------------------------------

	private void testSelectAllbyId() {
		String userid = "테스트";
		UserInfoVO uservo = userdao.select(userid);

		if (uservo == null) {
			logger.info("일치하는 ID가 없습니다.");
			return;
		}

		/*
		 * 유저아이디가 == 피드에서 쓴 유저 아이디가 일치해야함 
		 * 일치할 시 유저아이디의 피드가 최신순으로 출력
		 */

		List<FeedVO> selectuserid = feeddao.selectAllbyId(uservo.getUserId());

	    for (FeedVO vo : selectuserid) {
	        if(vo.getUserId().equals(uservo.getUserId())) { // 유저 아이디 비교
	        	logger.info("총 개수 : " + selectuserid.size() + "개");
	            logger.info("♠ 결과 : " + vo.toString());
	        } else {
	        	logger.info("♠ 피드가 없음!");
	        }
	    }
	}// end testSelectAllbyId
//		--------------------------------------------------
}
