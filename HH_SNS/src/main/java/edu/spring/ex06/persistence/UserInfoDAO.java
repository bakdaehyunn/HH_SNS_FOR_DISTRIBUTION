package edu.spring.ex06.persistence;

import java.util.Map;

import edu.spring.ex06.domain.UserInfoVO;

public interface UserInfoDAO {
	int insert(UserInfoVO vo);
	UserInfoVO select(String userId);
	int select(String userId,String userPassword);
	int update(UserInfoVO vo);
	int updateProfile(UserInfoVO vo);
	int delete(String userId);
}
