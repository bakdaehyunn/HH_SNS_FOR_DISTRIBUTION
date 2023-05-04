package edu.spring.ex06.persistence;


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
	public int delete(int likeId) {
		logger.info("★ LikeInfoDAOImple 좋아요 삭제");
		logger.info("좋아요 번호 : " + likeId);
		return sqlSession.delete(NAMESPACE + ".delete", likeId);
	}// end delete
//	-----------------------------------------------------------
	
//	int insert(LikeInfoVO vo);
//	int delete(int feedId);

	
}
