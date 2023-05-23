package edu.spring.ex06.domain;

public class FollowVO {
	private int followId;
	private String followerUserId;
	private String followingUserId;
	
	public FollowVO() {

	}

	public FollowVO(int followId, String followerUserId, String followingUserId) {
		this.followId = followId;
		this.followerUserId = followerUserId;
		this.followingUserId = followingUserId;
	}

	public int getFollowId() {
		return followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public String getFollowerUserId() {
		return followerUserId;
	}

	public void setFollowerUserId(String followerUserId) {
		this.followerUserId = followerUserId;
	}

	public String getFollowingUserId() {
		return followingUserId;
	}

	public void setFollowingUserId(String followingUserId) {
		this.followingUserId = followingUserId;
	}

	@Override
	public String toString() {
		return "FollowVO [followId=" + followId + ", followerUserId=" + followerUserId + ", followingUserId="
				+ followingUserId + "]";
	}
	

}
