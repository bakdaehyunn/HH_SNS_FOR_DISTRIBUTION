package edu.spring.ex06;

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
import edu.spring.ex06.persistence.FeedDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class FeedDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(FeedDAOTest.class);

	private static final String NAMESPACE = "edu.spring.ex06.FeedMapper";

	@Autowired
	private FeedDAO dao;

	@Test
	public void testDAO() {
//		testInsert();
//		testSelectAll();
//		testSelect();
//		testUpdate();
//		testDelete();
		testSelectAllbyId();
	}// end testDAO()

	// --------------------------------------------------
	private void testInsert() {
		FeedVO vo = new FeedVO(0, "피드내용", "유저아이디", "유저닉네임", "프로필사진", 0, 0, null, "음악제목");
		int result = dao.insert(vo);
		logger.info("♠ 결과 : " + result + "행 삽입");
	}// end testInsert

//	--------------------------------------------------

	private void testSelectAll() {
		List<FeedVO> list = dao.selectAll(null);
		logger.info("총 개수 : " + list.size() + "개");
		for (FeedVO vo : list) {
			logger.info("♠ 결과 : " + vo.toString());
		}
	}// end testSelectAll

//	--------------------------------------------------

	private void testSelect() {
		FeedVO vo = dao.select(null, 6);
		logger.info("♠ 결과 : " + vo.toString());
	}// end testSelect

//	--------------------------------------------------

	private void testUpdate() {
		FeedVO vo = new FeedVO(6, "2차 내용", null, "2차 닉네임", "2차 프로필", 0, 0, null, "2차 음악제목");
		int result = dao.update(null, 6, vo);

		if (result == 1) {
			logger.info("♠ 수정 성공");
		}

	}// end testUpdate

//	--------------------------------------------------

	private void testDelete() {
		FeedVO vo = new FeedVO(6, null, null, null, null, 0, 0, null, null);
		int result = dao.delete(null, 6);
		
		if(result == 1) {
			logger.info("♠ 삭제 성공");
		}

	}// end testDelete

//	--------------------------------------------------

	private void testSelectAllbyId() {
		List<FeedVO> list = dao.selectAllbyId("유저아이디");
		logger.info("총 개수 : " + list.size() + "개");
		for (FeedVO vo : list) {
			logger.info("♠ 결과 : " + vo.toString());
		}
		
	}// end testSelectAllbyId
}
