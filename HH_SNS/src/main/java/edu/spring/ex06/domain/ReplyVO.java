package edu.spring.ex06.domain;

import java.util.Date;

public class ReplyVO {
	private int replyId;  
	private int feedId;
	private String userId;
	private String userNickname;
	private String replyContent;
	private Date replyDate;
	private int likeCount;
	private int commentCount;
	
	public ReplyVO() {}
	
	

	public ReplyVO(int replyId, int feedId, String userId, String userNickname, String replyContent, Date replyDate,
			int likeCount, int commentCount) {
		super();
		this.replyId = replyId;
		this.feedId = feedId;
		this.userId = userId;
		this.userNickname = userNickname;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
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

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyId=" + replyId + ", feedId=" + feedId + ", userId=" + userId + ", userNickname="
				+ userNickname + ", replyContent=" + replyContent + ", replyDate=" + replyDate + ", likeCount="
				+ likeCount + ", commentCount=" + commentCount + "]";
	}
	
	
}
