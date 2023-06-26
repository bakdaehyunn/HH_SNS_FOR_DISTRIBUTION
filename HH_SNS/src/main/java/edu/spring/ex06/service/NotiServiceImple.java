package edu.spring.ex06.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex06.domain.NotiVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.FollowDAO;
import edu.spring.ex06.persistence.NotiDAO;


@Service
public class NotiServiceImple implements NotiService {
	private static Logger logger = 
			LoggerFactory.getLogger(NotiServiceImple.class);

	@Autowired
	private NotiDAO notiDAO;

	@Override
	public List<NotiVO> readList(String receiverId) { // 알림 리스트 불러오기
		logger.info("read() receiverId : " + receiverId);
		return notiDAO.selectList(receiverId);
	}

	@Override
	public int readCheck(String receiverId) { // 알림 확인
		logger.info("readCheck() receiverId : " + receiverId);
		return notiDAO.selectCheck(receiverId);
	}
	@Override
	public int update(String receiverId) { // 알림 읽음 변경
		logger.info("update() notiId : " + receiverId);
		return notiDAO.update(receiverId);
	}

	@Override
	public int delete(int notiId) { // 알림 삭제
		logger.info("delete() notiId : " + notiId);
		return notiDAO.deleteNotiId(notiId);
	}

	
	
	
	

}
