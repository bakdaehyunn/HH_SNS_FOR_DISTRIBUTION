package edu.spring.ex06.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;

public interface FollowService {
	int create(String followerUserId,String followingUserId);
	int readFollower(String followerUserId);
	int readFollowing(String followingUserId);
	int delete(String followerUserId, String followingUserId);
}
