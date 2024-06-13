package com.my.ex.dto.google;

import org.springframework.beans.factory.annotation.Value;

public class GoogleLoginRequestDto {
	
	@Value("${google.baseurl}")
	private String baseurl;
	
	@Value("${google.response_type}")
	private String response_type;
	
	@Value("${google.client_id}")
	private String client_id;
	
	@Value("${google.client_secret}")
	private String client_secret;
	
	@Value("${naver.redirect_uri}")
	private String redirect_uri;

	public GoogleLoginRequestDto() {}

	public GoogleLoginRequestDto(String baseurl, String response_type, String client_id, String client_secret,
			String redirect_uri) {
		this.baseurl = baseurl;
		this.response_type = response_type;
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.redirect_uri = redirect_uri;
	}

	public String getBaseurl() {
		return baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
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

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	@Override
	public String toString() {
		return "GoogleLoginRequestDto [baseurl=" + baseurl + ", response_type=" + response_type + ", client_id="
				+ client_id + ", client_secret=" + client_secret + ", redirect_uri=" + redirect_uri + "]";
	}

}
