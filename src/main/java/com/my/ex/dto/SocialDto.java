package com.my.ex.dto;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

//@Data
public class SocialDto {
	
	@Value("${naver.baseurl}")
	private String baseurl;
	private String response_type;
	private String client_id;
	private String client_secret;
	private String state;
	private String redirect_uri;
	
	public SocialDto() {}

	public SocialDto(String baseurl, String response_type, String client_id, String client_secret, String state,
			String redirect_uri) {
		this.baseurl = baseurl;
		this.response_type = response_type;
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.state = state;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	
	public void abc() {
		System.out.println("baseurl: " + baseurl);
		System.out.println("response_type: " + response_type);
		System.out.println("client_id: " + client_id);
		System.out.println("state: " + state);
		System.out.println("redirect_uri: " + redirect_uri);
	}
}
