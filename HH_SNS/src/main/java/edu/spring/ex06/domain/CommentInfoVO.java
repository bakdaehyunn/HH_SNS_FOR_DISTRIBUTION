package edu.spring.ex06.domain;

import java.util.Date;

public class CommentInfoVO {
	private int commentId;
	private int replyId;
	private String userId;
	private String userNickname;
	private String userProfile;
	private String commentContent;
	private Date commentDate;
	private int likeCount;
	
	public CommentInfoVO() {}

	public CommentInfoVO(int commentId, int replyId, String userId, String userNickname, String userProfile,
			String commentContent, Date commentDate, int likeCount) {
		super();
		this.commentId = commentId;
		this.replyId = replyId;
		this.userId = userId;
		this.userNickname = userNickname;
		this.userProfile = userProfile;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.likeCount = likeCount;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
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

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "CommentInfoVO [commentId=" + commentId + ", replyId=" + replyId + ", userId=" + userId
				+ ", userNickname=" + userNickname + ", userProfile=" + userProfile + ", commentContent="
				+ commentContent + ", commentDate=" + commentDate + ", likeCount=" + likeCount + "]";
	}

}
