package edu.spring.ex06.persistence;


import java.util.HashMap;
import java.util.List;
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
		logger.info("하트 들어갈 내용 : " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}// end insert
//	-----------------------------------------------------------

	@Override
	public LikeInfoVO select(int likeId) {
		logger.info("★ LikeInfoDAOImple 좋아요 번호 찾기");
		logger.info("likeId : " + likeId);
		return sqlSession.selectOne(NAMESPACE + ".select", likeId);
	}

//	-----------------------------------------------------------
	
	@Override
	public List<LikeInfoVO> select_check(String userId, int feedId) {
		logger.info("★ LikeInfoDAOImple 좋아요 중복확인");
		Map<String, Object>  args = new HashMap<>();
		args.put("userId", userId);
		args.put("feedId", feedId);
		return sqlSession.selectList(NAMESPACE + ".select_check", args);
	}
	
//	-----------------------------------------------------------
	
	@Override
	public List<LikeInfoVO> select_check_all_Id(String userId) {
		logger.info("★ LikeInfoDAOImple 좋아요 체크 유저 아이디 : " + userId );
		return sqlSession.selectList(NAMESPACE + ".select_check_all_userid", userId);
	}
	
//	-----------------------------------------------------------


	@Override
	public List<LikeInfoVO> select_all(int feedId) {
		logger.info("★ LikeInfoDAOImple 피드 전체 검색");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	}
	
//	-----------------------------------------------------------
	
	@Override
	public List<LikeInfoVO> select_all_Id(String userId) {
		logger.info("★ LikeInfoDAOImple 피드 전체 검색 좋아요");
		return sqlSession.selectList(NAMESPACE + ".select_all_id", userId);
	}

//	-----------------------------------------------------------

	@Override
	public int delete(int likeId) {
		logger.info("★ LikeInfoDAOImple 좋아요 삭제");
		return sqlSession.delete(NAMESPACE + ".delete", likeId);
	}// end delete
//	-----------------------------------------------------------

	@Override
	public int deleteUserid(String userId) {
		logger.info("★ LikeInfoDAOImple 좋아요 삭제");
		return sqlSession.delete(NAMESPACE + ".delete_userId", userId);
	}
	
}
