package com.my.ex.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

public interface IMapService {
	String findRoute(String type, Map<String, String> coordinates) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException;
	String readResponse(HttpURLConnection connection) throws IOException;
}
