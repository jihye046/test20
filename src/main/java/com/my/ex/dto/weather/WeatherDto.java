package com.my.ex.dto.weather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.my.ex.dto.weather.response.Main;
import com.my.ex.dto.weather.response.Sys;
import com.my.ex.dto.weather.response.Weather;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {
	private List<Weather> weather;
	private Main main;
	private Sys sys;
	private String dt_txt;
}
