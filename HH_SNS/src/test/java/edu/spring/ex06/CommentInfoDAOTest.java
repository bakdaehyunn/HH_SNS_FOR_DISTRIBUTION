package edu.spring.ex06;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.ex06.domain.CommentInfoVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.CommentInfoDAO;
import edu.spring.ex06.persistence.FeedDAO;
import edu.spring.ex06.persistence.ReplyDAO;
import edu.spring.ex06.persistence.UserInfoDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class CommentInfoDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(CommentInfoDAOTest.class);

	@Autowired
	private FeedDAO feeddao;

	@Autowired
	private UserInfoDAO userdao;
	
	@Autowired
	private ReplyDAO replydao;
	
	@Autowired
	private CommentInfoDAO commentdao;

	@Test
	public void testDAO() {
//		testInsert();
		testSelect();
//		testUpdate();
//		testDelete();
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
		
//		int commentId, int replyId, String userId, String userNickname, 
//		String userProfile, String commentContent, Date commentDate, int likeCount
		
//		Preparing: insert into comment_info 
//		(comment_id, reply_id, user_id, user_nickname, user_profile, comment_content) 
//		values (comment_info_seq.nextval, ?, ?, ?, ?, ?)

		CommentInfoVO commentvo = new CommentInfoVO(0, 7, userid, uservo.getUserNickname(), uservo.getUserProfile(), "아 배고파..", null, 0);

		int result = commentdao.insert(commentvo);
		logger.info("♠ 결과 : " + result + "행 삽입");

	}// end testInsert

//	--------------------------------------------------
	
	private void testSelect() {
		int commentId = 3;
		CommentInfoVO vo = commentdao.select(commentId);
		logger.info(vo.toString());
		
	}
	
//	--------------------------------------------------

	private void testUpdate() {
		String userid = "asss";
		UserInfoVO uservo = userdao.select(userid);

		// db에 아이디 있는지 체크
		if (uservo == null) {
			logger.info("ID가 없습니다.");
			return;
		}
		
		int result = commentdao.update(1, "ㅎ-ㅎ");

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

		int result = commentdao.delete(1);

		if (result == 1) {
			logger.info("♠ 삭제 성공");
		}

	}// end testDelete

//		--------------------------------------------------
}
