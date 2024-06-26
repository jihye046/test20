package com.my.ex.dto.map;

import org.springframework.beans.factory.annotation.Value;

public class NaverMapRequestDto {
	
	@Value("${naver.map.client_id}")
	private String client_id;
	
	@Value("${naver.map.client_secret}")
	private String client_secret;
	
	@Value("${naver.map.baseurl}")
	private String baseurl;
	
	public NaverMapRequestDto() {}

	public NaverMapRequestDto(String client_id, String client_secret, String baseurl) {
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.baseurl = baseurl;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getBaseurl() {
		return baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}

	@Override
	public String toString() {
		return "NaverMapRequestDto [client_id=" + client_id + ", client_secret=" + client_secret + ", baseurl="
				+ baseurl + "]";
	}

}
