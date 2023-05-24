package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;


public interface FollowDAO {
	int insert(String followerUserId, String followingUserId);
	int selectFollowerCnt(String followerUserId);
	int selectFollowingCnt(String followingUserid);
	int delete(String followerUserId, String followingUserId);
	List<UserInfoVO> selectFollowingList(String followingUserId);
	List<UserInfoVO> selectFollowerList(String followerUserId);
}
