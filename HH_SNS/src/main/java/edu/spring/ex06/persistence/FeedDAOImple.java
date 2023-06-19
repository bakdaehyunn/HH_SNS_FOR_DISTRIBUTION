package edu.spring.ex06.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;

@Repository
public class FeedDAOImple implements FeedDAO{
	private static final Logger logger = LoggerFactory.getLogger(FeedDAOImple.class);
	private static final String NAMESPACE = "edu.spring.ex06.FeedMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(FeedVO vo) {
		logger.info("★ FeedDAOImple 피드 등록");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}// end insert

	@Override
	public List<FeedVO> selectAll() {
		logger.info("★ FeedDAOImple 전체검색 최신순");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	}// end selectAll 전체 검색

	@Override
	public List<FeedVO> selectAllbyId(String userId) {
		logger.info("★ FeedDAOImple 개인 피드");
		return sqlSession.selectList(NAMESPACE +".select_all_by_userid", userId);
	}// end selectAllbyId 개인 피드 전체 출력
	
	@Override
	public FeedVO select(int feedId) {
		logger.info("★ FeedDAOImple 피드번호 detail");
		return sqlSession.selectOne(NAMESPACE + ".select", feedId);
	}// end select 상세 검색
	
	@Override
	public FeedVO select(String userId) {
		logger.info("★ FeedDAOImple 유저아이디 detail");
		return sqlSession.selectOne(NAMESPACE + ".select_by_userid", userId);
	}// end select 상세 검색

	@Override
	public int update_content(int feedId, String feedContent) {
		logger.info("★ FeedDAOImple 피드 수정");
		logger.info("feedId = " + feedId + " feedContent = " + feedContent);
		Map<String, Object>  args = new HashMap<>();
		args.put("feedId", feedId);
		args.put("feedContent", feedContent);
		return sqlSession.update(NAMESPACE + ".update_content", args);
	}// end update 수정

	@Override
	public int delete(int feedId) {
		logger.info("★ FeedDAOImple 피드 삭제");
		return sqlSession.delete(NAMESPACE + ".delete", feedId);
	}// end delete 삭제
	
	
	@Override
	public int updateLikeCnt(int amount, int feedId) {
		logger.info("★ FeedDAOImple 좋아요 개수 : feedId = " + feedId);
		Map<String, Integer> args = new HashMap<>();
		args.put("amount", amount);
		args.put("feedId", feedId);
		return sqlSession.update(NAMESPACE + ".update_like_cnt", args);
	}

	@Override
	public int updateReplyCnt(int amount, int feedId) {
		logger.info(" updateReplyCnt ");
		logger.info("amount : " + amount);
		logger.info("feedId : "+ feedId);
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("feedId", feedId);
		return sqlSession.update(NAMESPACE  + ".update_reply_cnt", args);
	}// end updateCommentCnt 개인 댓글 출력

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

	@Override
	public int deleteUserId(String userId) {
		logger.info("deleteUserId() : "+userId);
		return sqlSession.delete(NAMESPACE + ".delete_userId", userId);
	}

	


}
