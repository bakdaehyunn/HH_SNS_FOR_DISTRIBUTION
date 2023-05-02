package edu.spring.ex06.domain;

public class FollowerVO {
	private int followerId;
	private String userId;
	private String followerUserId;
	
	public FollowerVO() {}

	public FollowerVO(int followerId, String userId, String followerUserId) {
		super();
		this.followerId = followerId;
		this.userId = userId;
		this.followerUserId = followerUserId;
	}

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFollowerUserId() {
		return followerUserId;
	}

	public void setFollowerUserId(String followerUserId) {
		this.followerUserId = followerUserId;
	}

	@Override
	public String toString() {
		return "FollowerVO [followerId=" + followerId + ", userId=" + userId + ", followerUserId=" + followerUserId
				+ "]";
	}
}
