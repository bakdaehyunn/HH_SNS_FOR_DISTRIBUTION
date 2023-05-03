package edu.spring.ex06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.UserInfoDAO;

@Service
public class UserInfoServiceImple implements UserInfoService {
	private static Logger logger =
			LoggerFactory.getLogger(UserInfoServiceImple.class);
	
	@Autowired
	private UserInfoDAO dao;
	
	@Override
	public int create(UserInfoVO vo) {
		logger.info("create() 호출 : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public UserInfoVO read(String userId) {
		logger.info("read() 호출 : boardId = " + userId);
		return dao.select(userId);
	}

	@Override
	public int read(String userId, String UserPassword) {
		logger.info("read() 호출 ");
		logger.info("userId = " + userId);
		logger.info("UserPassword = " + UserPassword);
		return dao.select(userId, UserPassword);
	}

	@Override
	public int update(UserInfoVO vo) {
		logger.info("read() 호출 : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int updateProfile(UserInfoVO vo) {
		logger.info("readProfile() 호출 : vo = " + vo.toString());
		return dao.updateProfile(vo);
	}

	@Override
	public int delete(String userId) {
		logger.info("delete() 호출 : userId = " + userId);
		return dao.delete(userId);
	}

}
