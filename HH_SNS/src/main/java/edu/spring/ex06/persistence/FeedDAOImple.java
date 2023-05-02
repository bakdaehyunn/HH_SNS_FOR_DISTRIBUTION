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

	@Override
	public int insert(String userId, FeedVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}// end insert

	@Override
	public List<FeedVO> select(Date feedDate) {

		return null;
	}// end select 전체 검색

	@Override
	public FeedVO select(String userId, int feedId) {

		return null;
	}// end select 상세 검색

	@Override
	public int update(String userId, FeedVO vo) {

		return 0;
	}// end update 수정

	@Override
	public int delete(String usserId) {

		return 0;
	}// end delete 삭제

	@Override
	public List<FeedVO> selectAllById(String userId) {

		return null;
	}// end selectAllById 개인 피드 전체 출력

}
