package edu.spring.ex06.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;

public interface FollowService {
	int create(String followerUserId,String followingUserId);
	int readFollowerCnt(String followerUserId);
	int readFollowingCnt(String followingUserId);
	int delete(String followerUserId, String followingUserId);
	List<UserInfoVO> readFollowingList(String followingUserId);
	List<UserInfoVO> readFollowerList(String followerUserId);
	int readFollowingCheck(String followerUserId, String followingUserId);
}
