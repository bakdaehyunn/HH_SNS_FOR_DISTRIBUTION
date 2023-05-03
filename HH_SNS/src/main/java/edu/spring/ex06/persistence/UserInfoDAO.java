package edu.spring.ex06.persistence;

import edu.spring.ex06.domain.UserInfoVO;

public interface UserInfoDAO {
	int insert(UserInfoVO vo);
	UserInfoVO select(String userid);
	int update(UserInfoVO vo);
	int delete(String userid);
}
