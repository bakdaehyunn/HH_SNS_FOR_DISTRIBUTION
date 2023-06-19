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
	private NotiDAO dao;


	
	@Override
	public List<NotiVO> readList(String receiverId) {
		logger.info("read() receiverId : " + receiverId);
		return dao.selectList(receiverId);
	}

	@Override
	public int update(String receiverId) {
		logger.info("update() notiId : " + receiverId);
		return dao.update(receiverId);
	}

//	@Override
//	public int delete(int notiId) {
//		logger.info("delete() notiId : " + notiId);
//		return dao.delete(notiId);
//	}

	@Override
	public int readCheck(String receiverId) {
		logger.info("readCheck() receiverId : " + receiverId);
		return dao.selectCheck(receiverId);
	}
	
	
	

}
