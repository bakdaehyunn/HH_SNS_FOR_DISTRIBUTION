package edu.spring.ex06.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.ex06.domain.CommentInfoVO;
import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.LikeInfoVO;
import edu.spring.ex06.domain.ReplyVO;
import edu.spring.ex06.persistence.CommentInfoDAO;
import edu.spring.ex06.persistence.FeedDAO;
import edu.spring.ex06.persistence.LikeInfoDAO;
import edu.spring.ex06.persistence.ReplyDAO;

@Service
public class FeedServiceImple implements FeedService{
	private static final Logger logger =
			LoggerFactory.getLogger(FeedServiceImple.class);
	
	@Autowired
	private FeedDAO feeddao;
	
	@Autowired
	private LikeInfoDAO likedao;
	
	@Autowired
	private ReplyDAO replydao;
	
	@Autowired
	private CommentInfoDAO commentdao;

	@Override
	public int create(FeedVO feedvo) {
		return feeddao.insert(feedvo);
	}// end create()

	@Override
	public List<FeedVO> readAll() {
		logger.info("★ FeedServiceImple 전체 검색 ");
		return feeddao.selectAll();
	}
	
	@Override
	public List<FeedVO> readAllbyId(String userId) {
		logger.info("★ FeedServiceImple 아이디 전체 검색 ");
		return feeddao.selectAllbyId(userId);
	}
	
	@Override
	public FeedVO read(int feedId) {
		logger.info("★ FeedServiceImple 검색 : 피드 번호 = " + feedId);
		return feeddao.select(feedId);
	}
	
	@Override
	public FeedVO read(String userId) {
		logger.info("★ FeedServiceImple 검색 : 유저 아이디 = " + userId);
		return feeddao.select(userId);
	}
	@Override
	public int update_content(int feedId, String feedContent) {
		logger.info("★ FeedServiceImple 수정");
		logger.info("feedId = " + feedId + " feedContent = " + feedContent);
		return feeddao.update_content(feedId, feedContent);
	}
	
	@Transactional(value= "transactionManager")
	@Override
	public int delete(int feedId) {
		logger.info("★ FeedServiceImple 삭제 : " + feedId);
		feeddao.delete(feedId);
		logger.info("피드 삭제 완료");
		
		List<LikeInfoVO> likelist = likedao.select_all(feedId);
		for(LikeInfoVO likevo : likelist) {
			logger.info("??? : " + likevo.toString());
			likedao.delete(likevo.getLikeId());
			logger.info("좋아요 삭제 완료");			
		}
		
		List<ReplyVO> replylist = replydao.select(feedId);
		for(ReplyVO replyvo : replylist) {
			int replyId = replyvo.getReplyId();
			replydao.delete(replyId);
			logger.info("댓글 삭제 완료");
			
			List<CommentInfoVO> commentlist = commentdao.select_all(replyId);
			for(CommentInfoVO commentvo : commentlist) {
				commentdao.delete(commentvo.getCommentId());
				logger.info("대댓글 삭제 완료");
			}
			
		
		}
		
		
		
		return 1;
	}




}
