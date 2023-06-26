package edu.spring.ex06.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.ex06.domain.CommentInfoVO;
import edu.spring.ex06.domain.NotiVO;
import edu.spring.ex06.domain.ReplyVO;
import edu.spring.ex06.persistence.CommentInfoDAO;
import edu.spring.ex06.persistence.FeedDAO;
import edu.spring.ex06.persistence.NotiDAO;
import edu.spring.ex06.persistence.ReplyDAO;


@Service
public class ReplyServiceImple implements ReplyService{
	private static final Logger logger =
			LoggerFactory.getLogger(ReplyServiceImple.class);
	
	@Autowired
	private ReplyDAO replyDAO;
	
	@Autowired
	private FeedDAO feedDAO;
	
	@Autowired
	private NotiDAO notiDAO;
	
	@Autowired
	private CommentInfoDAO commentDAO; 
	
	// @Transactional
	// - 두 개의 데이터베이스 변경이 생길 때,
	// 위의 쿼리는 수행이 되었고, 아래 쿼리가 에러가 발생하면
	// 위의 퀴리는 rollback 되어야 한다.
	// 이런 기능을 제공해주는 Spring annotation
	// - root-context.xml에서 설정
	
	@Transactional(value = "transactionManager") // 트랜잭션 적용
	@Override
	public int create(ReplyVO vo,String feedUserId) throws Exception{ // 댓글 생성
		
		// 댓글(test_reply)의 데이터가 증가하면
		// 게시판(test_board)의 댓글 개수(reply_cnt)가 변경되어야 한다.
		// test_reply 테이블에 insert를 수행하면
		// test_board 테이블에 update를 수행한다.
		
		logger.info("create() 호출 : vo = " + vo.toString());
		replyDAO.insert(vo); // 댓글 등록
		if(!(vo.getUserId().equals(feedUserId))) { //다른 유저 피드에 댓글 단 경우
			NotiVO notiVO = new NotiVO(0, vo.getUserId(), feedUserId, "reply", 0, vo.getFeedId());
			notiDAO.insert(notiVO); //알림 등록
		}
		logger.info("댓글 입력 성공");
		feedDAO.updateReplyCnt(1, vo.getFeedId()); // 댓글 카운트 증가
		return 1;
	}

	@Override
	public List<ReplyVO> read(int feedId) {
		logger.info("read() 호출 : feedId = " + feedId);
		return replyDAO.select(feedId);
	}

	@Override
	public int update(int replyId, String replyContent) {
		logger.info("update() 호출");
		logger.info("replyID = " + replyId +", replyContent = "+ replyContent);
		return replyDAO.update(replyId, replyContent);
	}
	
	@Transactional(value = "transactionManager") //트랜잭션 적용
	@Override
	public int delete(int replyId, int feedId) throws  Exception{
		logger.info("delete() 호출 : replyId = " + replyId);
		replyDAO.delete(replyId); // 댓글 삭제
		logger.info("댓글 삭제 성공");
		feedDAO.updateReplyCnt(-1, feedId); // 댓글 카운트 감소
		
		List<CommentInfoVO> list = commentDAO.select_all(replyId);// 해당 댓글의 대댓글 정보 불러오기
		for(CommentInfoVO commentvo : list) { 
			int commentId = commentvo.getCommentId();
			commentDAO.delete(commentId);// 대댓글 삭제
			logger.info("대댓글 삭제 성공");
		}
		
		return 1;
	}

}
