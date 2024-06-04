package com.my.ex.dto;

public class NaverProfileApi {
	private String resultcode;
	private String message;
	private NaverUserInfo response;
	
	public NaverProfileApi() {}

	public NaverProfileApi(String resultcode, String message, NaverUserInfo response) {
		this.resultcode = resultcode;
		this.message = message;
		this.response = response;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NaverUserInfo getResponse() {
		return response;
	}

	public void setResponse(NaverUserInfo response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "NaverProfileApi [resultcode=" + resultcode + ", message=" + message + ", response=" + response + "]";
	}
	
}
