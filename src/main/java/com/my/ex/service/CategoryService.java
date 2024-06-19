package com.my.ex.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.my.ex.dto.weather.WeatherRequestDto;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private WeatherRequestDto weatherDto;
	
	@Override
	public String getWeather(String type, double latitude, double longitude) {
		UriComponents uriComponents = UriComponentsBuilder
				.fromHttpUrl(weatherDto.getBaseurl() + "/" + type)
				.queryParam("lat", latitude)
				.queryParam("lon", longitude)
				.queryParam("lang", "kr")
				.queryParam("appid", weatherDto.getWeather_key())
				.build();
		try {
			URL url = new URL(uriComponents.toString());
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			return readResponse(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String readResponse(HttpURLConnection connection) throws IOException {
		int responseCode = connection.getResponseCode();
		BufferedReader br;
		
		br = (responseCode == 200) 
			? new BufferedReader(new InputStreamReader(connection.getInputStream()))
			: new BufferedReader(new InputStreamReader(connection.getErrorStream()));
		String inputLine;
		StringBuffer bf = new StringBuffer();
		while((inputLine = br.readLine()) != null) {
			bf.append(inputLine);
		}
		br.close();
		return bf.toString();
	}

}
