package edu.spring.ex06.service;

import edu.spring.ex06.domain.UserInfoVO;

public interface UserInfoService {
	int create(UserInfoVO vo);
	UserInfoVO read(String userId);
	int read(String userId, String password);
	int readUserId(String userId);
	int readUserEmail(String userEmail);
	int update(UserInfoVO vo);
	int updateProfile(UserInfoVO vo)throws Exception;
	int delete(String userId);
	
	
}
