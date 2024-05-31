package com.my.ex.service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//@Service
@Component
@PropertySource("classpath:config/email.properties")
public class SocialLogin {
	
	@Value("${naver.baseurl}")
	private String baseurl;
	
	@Value("${naver.response_type}")
	private String response_type;
	
	@Value("${naver.client_id}")
	private String client_id;
	
//	@Value("${naver.client_secret}")
//	private String client_secret;
	
	@Value("${naver.state}")
	private String state;

	@Value("${naver.redirect_uri}")
	private String redirect_uri;
	
	public String getNaverAuthorizeUrl(String type) throws URISyntaxException{
		System.out.println("baseurl: " + baseurl);
		System.out.println("response_type: " + response_type);
		System.out.println("client_id: " + client_id);
		System.out.println("state: " + state);
		System.out.println("redirect_uri: " + redirect_uri);
		
		UriComponents uriComponents = null;
		try {
			uriComponents = UriComponentsBuilder
					.fromUriString(baseurl + "/" + type)
					.queryParam("response_type", response_type)
					.queryParam("client_id", client_id)
					.queryParam("state", URLEncoder.encode(state, "UTF-8"))
					.queryParam("redirect_uri", URLEncoder.encode(redirect_uri, "UTF-8"))
					.build();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return uriComponents.toString();
	}
}
