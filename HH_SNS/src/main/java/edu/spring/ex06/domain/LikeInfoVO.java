package edu.spring.ex06.domain;

import java.util.Date;

public class LikeInfoVO {
	private int likeId;
	private String userId;
	private int feedId;
	private int replyId;
	private int commentId;
	private Date likeDate;
	
	public LikeInfoVO() {}

	public LikeInfoVO(int likeId, String userId, int feedId, int replyId, int commentId, Date likeDate) {
		super();
		this.likeId = likeId;
		this.userId = userId;
		this.feedId = feedId;
		this.replyId = replyId;
		this.commentId = commentId;
		this.likeDate = likeDate;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

	@Override
	public String toString() {
		return "LikeInfoVO [likeId=" + likeId + ", userId=" + userId + ", feedId=" + feedId + ", replyId=" + replyId
				+ ", commentId=" + commentId + ", likeDate=" + likeDate + "]";
	}

	

}
