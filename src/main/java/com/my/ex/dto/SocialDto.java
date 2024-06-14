package com.my.ex.dto;

import java.sql.Date;

public class SocialDto {
	private String sns_id;
	private String sns_nickName;
	private String upoint;
	private String sns_email;
	private String sns_name;
	private String sns_mobile;
	private String sns_type;
	private String sns_profile;
	private Date sns_connect_date;
	
	public SocialDto() {}

	public SocialDto(String sns_id, String sns_nickName, String upoint, String sns_email, String sns_name,
			String sns_mobile, String sns_type, String sns_profile, Date sns_connect_date) {
		this.sns_id = sns_id;
		this.sns_nickName = sns_nickName;
		this.upoint = upoint;
		this.sns_email = sns_email;
		this.sns_name = sns_name;
		this.sns_mobile = sns_mobile;
		this.sns_type = sns_type;
		this.sns_profile = sns_profile;
		this.sns_connect_date = sns_connect_date;
	}

	public String getSns_id() {
		return sns_id;
	}

	public void setSns_id(String sns_id) {
		this.sns_id = sns_id;
	}

	public String getSns_nickName() {
		return sns_nickName;
	}

	public void setSns_nickName(String sns_nickName) {
		this.sns_nickName = sns_nickName;
	}

	public String getUpoint() {
		return upoint;
	}

	public void setUpoint(String upoint) {
		this.upoint = upoint;
	}

	public String getSns_email() {
		return sns_email;
	}

	public void setSns_email(String sns_email) {
		this.sns_email = sns_email;
	}

	public String getSns_name() {
		return sns_name;
	}

	public void setSns_name(String sns_name) {
		this.sns_name = sns_name;
	}

	public String getSns_mobile() {
		return sns_mobile;
	}

	public void setSns_mobile(String sns_mobile) {
		this.sns_mobile = sns_mobile;
	}

	public String getSns_type() {
		return sns_type;
	}

	public void setSns_type(String sns_type) {
		this.sns_type = sns_type;
	}

	public String getSns_profile() {
		return sns_profile;
	}

	public void setSns_profile(String sns_profile) {
		this.sns_profile = sns_profile;
	}

	public Date getSns_connect_date() {
		return sns_connect_date;
	}

	public void setSns_connect_date(Date sns_connect_date) {
		this.sns_connect_date = sns_connect_date;
	}

	@Override
	public String toString() {
		return "NaverDto [sns_id=" + sns_id + ", sns_nickName=" + sns_nickName + ", upoint=" + upoint + ", sns_email="
				+ sns_email + ", sns_name=" + sns_name + ", sns_mobile=" + sns_mobile + ", sns_type=" + sns_type
				+ ", sns_profile=" + sns_profile + ", sns_connect_date=" + sns_connect_date + "]";
	}
	
}
