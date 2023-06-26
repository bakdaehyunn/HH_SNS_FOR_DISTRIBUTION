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
	public int insert(NotiVO vo) { // 알림 등록
		logger.info("insert() 호출");
		logger.info("VO :"+vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int selectCheck(String receiverId) { // 알림 확인
		logger.info("select_check() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_check", receiverId);
	}

	@Override
	public List<NotiVO> selectList(String receiverId) { // 알림 리스트 불러오기
		logger.info("select() 호출");
		logger.info("receiverId : " + receiverId);
		return sqlSession.selectList(NAMESPACE + ".select_list", receiverId);
	}

	@Override
	public int update(String receiverId) { // 알림 읽음 변경
		logger.info("update() 호출 notiId : " + receiverId);
		return sqlSession.update(NAMESPACE + ".update", receiverId);
	}


	@Override
	public int deleteNotiId(int notiId) { // 알림 아이디로 알림 삭제
		logger.info("deleteNotiId : " +notiId);
		return sqlSession.delete(NAMESPACE + ".delete_notiId", notiId);
	}

	@Override
	public int delete(String senderId, String receiverId) { //팔로우정보로 알림 삭제
		logger.info("delete() 호출");
		logger.info("senderId : " + senderId);
		logger.info("receiverId : " + receiverId);
		Map<String,String> args = new HashMap<String, String>();
		args.put("senderId", senderId);
		args.put("receiverId", receiverId);
		return sqlSession.update(NAMESPACE + ".delete", args);
	}
	
	@Override
	public int deleteUserId(String userId) { // 회원 정보로 알림 삭제
		logger.info("deleteReceiverId : " + userId);
		return sqlSession.delete(NAMESPACE + ".delete_userId", userId);

	}

	

}
