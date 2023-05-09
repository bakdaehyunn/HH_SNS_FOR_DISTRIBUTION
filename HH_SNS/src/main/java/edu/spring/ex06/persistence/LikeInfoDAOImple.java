package edu.spring.ex06.persistence;


import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex06.domain.LikeInfoVO;

@Repository
public class LikeInfoDAOImple implements LikeInfoDAO{
	private static Logger logger =
			LoggerFactory.getLogger(LikeInfoDAOImple.class);
	private static final String NAMESPACE =
			"edu.spring.ex06.LikeInfoMapper";

	@Autowired
	private SqlSession sqlSession;
//	-----------------------------------------------------------
	@Override
	public int insert(LikeInfoVO vo) {
		logger.info("★ LikeInfoDAOImple 좋아요 등록");
		logger.info(vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}// end insert
//	-----------------------------------------------------------

	@Override
	public int update(int likeId, int feedId, int replyId, int commentId) {
		logger.info("★ LikeInfoDAOImple 좋아요 수정");
		logger.info("좋아요 번호 : " + likeId + ", 게시글 번호 : " + feedId + ", 댓글 번호 : " + replyId + ", 대댓글 번호 : " + commentId);
		Map<String, Object>  args = new HashMap<>();
		args.put("likeId", likeId);
		args.put("feedId", feedId);
		args.put("replyId", replyId);
		args.put("commentId", commentId);
		return sqlSession.update(NAMESPACE + ".update", args);
	}// end update
	
//	-----------------------------------------------------------

	@Override
	public int delete(int likeId) {
		logger.info("★ LikeInfoDAOImple 좋아요 삭제");
		logger.info("좋아요 번호 : " + likeId);
		return sqlSession.delete(NAMESPACE + ".delete", likeId);
	}// end delete
//	-----------------------------------------------------------

	
//	int insert(LikeInfoVO vo);
//	int delete(int feedId);

	
}
