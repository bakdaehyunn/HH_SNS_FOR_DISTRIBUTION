package edu.spring.ex06.domain;

import java.util.Date;

public class UserInfoVO {
	private String userId; 
	private String userPassword;
	private String userName;
	private String userNickname;
	private Date userBirth;
	private String userEmail;
	private String userProfile;
	
	public UserInfoVO() {}

	public UserInfoVO(String userId, String userPassword, String userName, String userNickname, Date userBirth,
			String userEmail, String userProfile) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userNickname = userNickname;
		this.userBirth = userBirth;
		this.userEmail = userEmail;
		this.userProfile = userProfile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public Date getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "UserInfoVO [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName
				+ ", userNickname=" + userNickname + ", userBirth=" + userBirth + ", userEmail=" + userEmail
				+ ", userProfile=" + userProfile + "]";
	}
	
}
