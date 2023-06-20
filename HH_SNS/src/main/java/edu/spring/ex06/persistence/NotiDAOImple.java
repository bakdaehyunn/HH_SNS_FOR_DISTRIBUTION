package edu.spring.ex06.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex06.domain.NotiVO;

@Repository
public class NotiDAOImple implements NotiDAO {
	private static final Logger logger = LoggerFactory.getLogger(NotiDAOImple.class);
	private static final String NAMESPACE = "edu.spring.ex06.NotiMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(NotiVO vo) {
		logger.info("insert() 호출");
		logger.info("VO :"+vo.toString());
//		logger.info("senderId : " + senderId);
//		logger.info("receiverId : " + receiverId);
//		logger.info("notiCategory : "  + notiCategory);
//		Map<String,String> args = new HashMap<String, String>();
//		args.put("senderId", senderId);
//		args.put("receiverId", receiverId);
//		args.put("notiCategory", notiCategory);
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<NotiVO> selectList(String receiverId) {
		logger.info("select() 호출");
		logger.info("receiverId : " + receiverId);
		return sqlSession.selectList(NAMESPACE + ".select_list", receiverId);
	}

	@Override
	public int delete(String senderId, String receiverId) {
		logger.info("delete() 호출");
		logger.info("senderId : " + senderId);
		logger.info("receiverId : " + receiverId);
		Map<String,String> args = new HashMap<String, String>();
		args.put("senderId", senderId);
		args.put("receiverId", receiverId);
		return sqlSession.update(NAMESPACE + ".delete", args);
	}

	@Override
	public int update(String receiverId) {
		logger.info("update() 호출 notiId : " + receiverId);
		return sqlSession.update(NAMESPACE + ".update", receiverId);
	}

	@Override
	public int selectCheck(String receiverId) {
		logger.info("select_check() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_check", receiverId);
	}



	@Override
	public int deleteUserId(String receiverId) {
		logger.info("deleteReceiverId : " +receiverId);
		return sqlSession.delete(NAMESPACE + ".delete_receiverId", receiverId);

	}

	@Override
	public int deleteNotiId(int notiId) {
		logger.info("deleteNotiId : " +notiId);
		return sqlSession.delete(NAMESPACE + ".delete_notiId", notiId);
	}

}
