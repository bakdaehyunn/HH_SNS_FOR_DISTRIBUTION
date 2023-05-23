package edu.spring.ex06.domain;

import java.util.Date;

public class FeedVO {
	private int feedId;
	private String feedContent;
	private String userId;
	private String userNickname;
	private String userProfile;
	private int replyCount;
	private int likeCount;
	private Date feedDate;
	private String musicTitle;
	private String feedPicture;
	
	public FeedVO() {}

	public FeedVO(int feedId, String feedContent, String userId, String userNickname, String userProfile,
			int replyCount, int likeCount, Date feedDate, String musicTitle, String feedPicture) {
		super();
		this.feedId = feedId;
		this.feedContent = feedContent;
		this.userId = userId;
		this.userNickname = userNickname;
		this.userProfile = userProfile;
		this.replyCount = replyCount;
		this.likeCount = likeCount;
		this.feedDate = feedDate;
		this.musicTitle = musicTitle;
		this.feedPicture = feedPicture;
	}

	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	public String getFeedContent() {
		return feedContent;
	}

	public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public Date getFeedDate() {
		return feedDate;
	}

	public void setFeedDate(Date feedDate) {
		this.feedDate = feedDate;
	}

	public String getMusicTitle() {
		return musicTitle;
	}

	public void setMusicTitle(String musicTitle) {
		this.musicTitle = musicTitle;
	}

	@Override
	public String toString() {
		return "FeedVO [feedId=" + feedId + ", feedContent=" + feedContent + ", userId=" + userId + ", userNickname="
				+ userNickname + ", userProfile=" + userProfile + ", replyCount=" + replyCount + ", likeCount="
				+ likeCount + ", feedDate=" + feedDate + ", musicTitle=" + musicTitle + "]";
	}

}
