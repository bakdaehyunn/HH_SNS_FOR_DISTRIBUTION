package edu.spring.ex06.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.spring.ex06.domain.ReplyVO;


@Repository
public class ReplyDAOImple implements ReplyDAO{
	private static final Logger logger =
			org.slf4j.LoggerFactory.getLogger(ReplyDAOImple.class);
	private static final String NAMESPACE = "edu.spring.ex06.ReplyMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ReplyVO vo) { // 댓글 생성
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ReplyVO> select(int feedId) { // 댓글 리스트 불러오기
		logger.info("select() 호출 : boardId = " + feedId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_feed_id", feedId) ;
	}
	
	@Override
	public ReplyVO selectFeedId(int replyId) { //댓글 정보 불러오기
		logger.info("selectFeedId replyId : " + replyId);
		return sqlSession.selectOne(NAMESPACE + ".select_feedid_by_replyid", replyId);
	}
	
	@Override
	public int update(int replyId, String replyContent) { // 댓글 수정
		logger.info("update() 호출");
		logger.info("replyId = " + replyId + ", replyContent = "+ replyContent);
		Map<String, Object> args = new HashMap();
		args.put("replyId", replyId);
		args.put("replyContent", replyContent);
		return sqlSession.update(NAMESPACE+ ".update",args);
	}

	@Override
	public int updateProfile(String userNickname, String userProfile, String userId) {  //프로필 정보 변경
		logger.info("updateProfile()");
		logger.info("userNickname : " + userNickname);
		logger.info("userProfile : "  + userProfile);
		logger.info("userId : " + userId);
		Map<String, String>  args = new HashMap<>();
		args.put("userNickname", userNickname);
		args.put("userProfile", userProfile);
		args.put("userId", userId);
		logger.info(args.toString());
		return sqlSession.update(NAMESPACE + ".update_profile", args);
	}
	
	@Override
	public int updateCommentCnt(int amount, int replyId) { // 대댓글 카운트 증감
		logger.info("updateCommentCnt");
		logger.info("amount : " + amount);
		logger.info("replyId : " + replyId);
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("replyId", replyId);
		return sqlSession.update(NAMESPACE + ".update_comment_cnt", args);
	}

	@Override
	public int deleteUserId(String userId) { // 유저아이디로 댓글 삭제
		logger.info("deleteUserId() :" + userId);
		return sqlSession.delete(NAMESPACE + ".delete_userId", userId);
	}
	
	@Override
	public int delete(int replyId) { // 댓글아이디로 댓글 삭제
		logger.info("update() 호출 : boardId = " + replyId);
		return sqlSession.delete(NAMESPACE+ ".delete", replyId);
	}

}
