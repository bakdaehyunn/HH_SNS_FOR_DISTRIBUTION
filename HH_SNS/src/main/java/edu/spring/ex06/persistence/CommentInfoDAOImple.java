package edu.spring.ex06.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex06.domain.CommentInfoVO;


@Repository
public class CommentInfoDAOImple implements CommentInfoDAO{
	private static final Logger logger =
			org.slf4j.LoggerFactory.getLogger(CommentInfoDAOImple.class);
	private static final String NAMESPACE = "edu.spring.ex06.CommentInfoMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	

	@Override
	public int insert(CommentInfoVO vo) {
		logger.info("★ CommentInfoDAOImple 대댓글 등록 : " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	
	@Override
	public CommentInfoVO select(int commentId) {
		logger.info("★ CommentInfoDAOImple 대댓글 번호 찾기 : " + commentId);
		return sqlSession.selectOne(NAMESPACE + ".select_commentid", commentId);
	}	

	@Override
	public CommentInfoVO select_reply(int replyId) {
		logger.info("★ CommentInfoDAOImple 대댓글의 댓글번호 찾기 : " + replyId);
		return sqlSession.selectOne(NAMESPACE + ".select_replyid", replyId);
	}

	@Override
	public List<CommentInfoVO> select_all(int replyId) {
		logger.info("★ CommentInfoDAOImple 대댓글 전체 찾기 : " + replyId);
		return sqlSession.selectList(NAMESPACE + ".select_all_replyid", replyId);
	}
	
	@Override
	public List<CommentInfoVO> select_all_commentid(int commentId) {
		logger.info("★ CommentInfoDAOImple 대댓글 전체 찾기 : " + commentId);
		return sqlSession.selectList(NAMESPACE + ".select_all_commentid", commentId);
	}
	
	@Override
	public int update(int commentId, String commentContent) {
		logger.info("★ CommentInfoDAOImple 대댓글 수정 : 대댓글 번호 = " + commentId + ", 대댓글 내용 = " + commentContent);
		Map<String, Object> args = new HashMap();
		args.put("commentId", commentId);
		args.put("commentContent", commentContent);
		return sqlSession.update(NAMESPACE+ ".update",args);
	}

	@Override
	public int delete(int commentId) {
		logger.info("★ CommentInfoDAOImple 대댓글 삭제 번호 : " + commentId);
		return sqlSession.delete(NAMESPACE+ ".delete", commentId);
	}

	@Override
	public int update_profile(String userNickname, String userProfile, String userId) {
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




}
