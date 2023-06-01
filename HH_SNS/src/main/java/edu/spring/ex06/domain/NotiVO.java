package edu.spring.ex06.domain;

public class NotiVO {
	private int notiId;
	private String senderId;
	private String receiverId;
	private String notiCategory;
	private int notiRead;
	private int feedId;

	public NotiVO() {
		
	}
	
	public NotiVO(int notiId, String senderId, String receiverId, String notiCategory, int notiRead, int feedId) {
		this.notiId = notiId;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.notiCategory = notiCategory;
		this.notiRead = notiRead;
		this.feedId = feedId;
	}
	
	public int getNotiId() {
		return notiId;
	}
	public void setNotiId(int notiId) {
		this.notiId = notiId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getNotiCategory() {
		return notiCategory;
	}
	public void setNotiCategory(String notiCategory) {
		this.notiCategory = notiCategory;
	}
	public int getNotiRead() {
		return notiRead;
	}
	public void setNotiRead(int notiRead) {
		this.notiRead = notiRead;
	}
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	
	@Override
	public String toString() {
		return "NotiVO [notiId=" + notiId + ", senderId=" + senderId + ", receiverId=" + receiverId + ", notiCategory="
				+ notiCategory + ", notiRead=" + notiRead + ", feedId=" + feedId + "]";
	}
	
}
