package edu.spring.ex06.persistence;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex06.domain.FeedVO;

@Repository
public class FeedDAOImple implements FeedDAO{
	private static final Logger logger = LoggerFactory.getLogger(FeedDAOImple.class);
	private static final String NAMESPACE = "edu.spring.ex06.FeedMapper";

	@Autowired
	private SqlSession sqlSession;
	
	/*
	 * int insert(String userId, FeedVO vo); // 피드 작성(회원)
	   List<FeedVO> selectAll(String userId, Date feedDate); // 전체 피드 츌력 = 전체 검색 (최신순)(비회원)
	   FeedVO select (String userId, int feedId); // 피드 상세 출력 = 아이디 기준 검색(비회원)
	   int update(String userId, FeedVO vo); // 피드 수정(회원)
	   int delete(String usserId); // 아이디 기준 피드 삭제(회원)
	   List<FeedVO> selectAllbyId (String userId); // 개인 피드 출력 = 아이디 기준(회원)
	 * */

	@Override
	public int insert(FeedVO vo) {
		logger.info("★ FeedDAOImple 피드 등록");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}// end insert

	@Override
	public List<FeedVO> selectAll(Date feedDate) {
		logger.info("★ FeedDAOImple 전체검색 최신순");
		return sqlSession.selectList(NAMESPACE + ".select_all", feedDate);
	}// end selectAll 전체 검색

	@Override
	public FeedVO select(String userId, int feedId) {
		logger.info("★ FeedDAOImple 피드 detail");
		return sqlSession.selectOne(NAMESPACE + ".select", feedId);
	}// end select 상세 검색

	@Override
	public int update(String userId, int feedId, FeedVO vo) {
		logger.info("★ FeedDAOImple 피드 수정");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}// end update 수정

	@Override
	public int delete(String usserId, int feedId) {
		logger.info("★ FeedDAOImple 피드 삭제");
		return sqlSession.delete(NAMESPACE + ".delete", feedId);
	}// end delete 삭제

	@Override
	public List<FeedVO> selectAllbyId(String userId) {
		logger.info("★ FeedDAOImple 개인 피드");
		return sqlSession.selectList(NAMESPACE +".select_all_by_id", userId);
	}// end selectAllbyId 개인 피드 전체 출력

}
