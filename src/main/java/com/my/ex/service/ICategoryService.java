package com.my.ex.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface ICategoryService {
	String getWeather(String type, double latitude, double longitude) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException;
	String getAddress(String type) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException;
	String readResponse(HttpURLConnection connection) throws IOException;
}
