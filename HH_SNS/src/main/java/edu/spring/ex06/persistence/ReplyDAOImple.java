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
	public int insert(ReplyVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ReplyVO> select(int feedId) {
		logger.info("select() 호출 : boardId = " + feedId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_feed_id", feedId) ;
	}

	@Override
	public int update(int replyId, String replyContent) {
		logger.info("update() 호출");
		logger.info("replyId = " + replyId + ", replyContent = "+ replyContent);
		Map<String, Object> args = new HashMap();
		args.put("replyId", replyId);
		args.put("replyContent", replyContent);
		return sqlSession.update(NAMESPACE+ ".update",args);
	}

	@Override
	public int delete(int replyId) {
		logger.info("update() 호출 : boardId = " + replyId);
		return sqlSession.delete(NAMESPACE+ ".delete", replyId);
	}

	@Override
	public int updateCommentCnt(int amount, int replyId) {
		logger.info("updateCommentCnt");
		logger.info("amount : " + amount);
		logger.info("replyId : " + replyId);
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("reply", replyId);
		return sqlSession.update(NAMESPACE + ".update_comment_cnt", args);
	}

	@Override
	public int selectFeedId(int replyId) {
		logger.info("selectFeedId replyId : " + replyId);
		return sqlSession.selectOne(NAMESPACE + ".select_feedid_by_replyid", replyId);
	}

}
