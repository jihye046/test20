package com.my.ex.dto.naver;

public class NaverUserInfo {
	private String id;
	private String nickname;
	private String name;
	private String email;
	private String gender;
	private String age;
	private String birthday;
	private String profile_image;
	private String birthyear;
	private String mobile;
	private String mobile_e164;
	
	public NaverUserInfo() {}

	public NaverUserInfo(String id, String nickname, String name, String email, String gender, String age,
			String birthday, String profile_image, String birthyear, String mobile, String mobile_e164) {
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.birthday = birthday;
		this.profile_image = profile_image;
		this.birthyear = birthyear;
		this.mobile = mobile;
		this.mobile_e164 = mobile_e164;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile_e164() {
		return mobile_e164;
	}

	public void setMobile_e164(String mobile_e164) {
		this.mobile_e164 = mobile_e164;
	}

	@Override
	public String toString() {
		return "NaverUserInfo [id=" + id + ", nickname=" + nickname + ", name=" + name + ", email=" + email
				+ ", gender=" + gender + ", age=" + age + ", birthday=" + birthday + ", profile_image=" + profile_image
				+ ", birthyear=" + birthyear + ", mobile=" + mobile + ", mobile_e164=" + mobile_e164 + "]";
	}

}
