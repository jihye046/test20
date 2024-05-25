package com.my.ex.dto;

public class UserDto {
	private String userId;
	private String userPw;
	private String unickName;
	private int upoint;
	private String uemail;

	public UserDto() {}

	public UserDto(String userId, String userPw, String unickName, int upoint, String uemail) {
		this.userId = userId;
		this.userPw = userPw;
		this.unickName = unickName;
		this.upoint = upoint;
		this.uemail = uemail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUnickName() {
		return unickName;
	}

	public void setUnickName(String unickName) {
		this.unickName = unickName;
	}

	public int getUpoint() {
		return upoint;
	}

	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	
}
