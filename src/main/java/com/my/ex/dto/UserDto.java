package com.my.ex.dto;

import java.sql.Date;

public class UserDto {
	private String username;
	private String umobile;
	private String userId;
	private String userPw;
	private String unickName;
	private String uemail;
	private int upoint;
	private Date create_date;
	private Date modify_date;
	
	public UserDto() {}

	public UserDto(String username, String umobile, String userId, String userPw, String unickName, String uemail,
			int upoint, Date create_date, Date modify_date) {
		this.username = username;
		this.umobile = umobile;
		this.userId = userId;
		this.userPw = userPw;
		this.unickName = unickName;
		this.uemail = uemail;
		this.upoint = upoint;
		this.create_date = create_date;
		this.modify_date = modify_date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUmobile() {
		return umobile;
	}

	public void setUmobile(String umobile) {
		this.umobile = umobile;
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

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public int getUpoint() {
		return upoint;
	}

	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	@Override
	public String toString() {
		return "UserDto [username=" + username + ", umobile=" + umobile + ", userId=" + userId + ", userPw=" + userPw
				+ ", unickName=" + unickName + ", uemail=" + uemail + ", upoint=" + upoint + ", create_date="
				+ create_date + ", modify_date=" + modify_date + "]";
	}

}
