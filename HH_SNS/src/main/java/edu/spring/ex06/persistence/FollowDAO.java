package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.FeedVO;


public interface FollowDAO {
	int insert(String followerUserId, String followingUserId);
	int selectFollower(String followerUserId);
	int selectFollowing(String followingUserid);
	int delete(String followerUserId, String followingUserId);
}
