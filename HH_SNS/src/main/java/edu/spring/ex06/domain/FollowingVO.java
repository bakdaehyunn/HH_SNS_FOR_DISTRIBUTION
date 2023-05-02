package edu.spring.ex06.domain;

public class FollowingVO {
	private int followingId;
	private String userid;
	private String followingUserId;
	
	public FollowingVO() {}

	public FollowingVO(int followingId, String userid, String followingUserId) {
		super();
		this.followingId = followingId;
		this.userid = userid;
		this.followingUserId = followingUserId;
	}

	public int getFollowingId() {
		return followingId;
	}

	public void setFollowingId(int followingId) {
		this.followingId = followingId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFollowingUserId() {
		return followingUserId;
	}

	public void setFollowingUserId(String followingUserId) {
		this.followingUserId = followingUserId;
	}

	@Override
	public String toString() {
		return "FollowingVO [followingId=" + followingId + ", userid=" + userid + ", followingUserId=" + followingUserId
				+ "]";
	}
}
