package com.my.ex.dto.weather;

import org.springframework.beans.factory.annotation.Value;

public class WeatherRequestDto {
	
	@Value("${weather.key}")
	private String weather_key;
	
	@Value("${weather.baseurl}")
	private String baseurl;

	public WeatherRequestDto() {}

	public WeatherRequestDto(String weather_key, String baseurl) {
		this.weather_key = weather_key;
		this.baseurl = baseurl;
	}

	public String getWeather_key() {
		return weather_key;
	}

	public void setWeather_key(String weather_key) {
		this.weather_key = weather_key;
	}

	public String getBaseurl() {
		return baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}

	@Override
	public String toString() {
		return "WeatherDto [weather_key=" + weather_key + ", baseurl=" + baseurl + "]";
	}
	
}
