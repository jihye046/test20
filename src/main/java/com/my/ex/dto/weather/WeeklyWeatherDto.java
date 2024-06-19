package com.my.ex.dto.weather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeeklyWeatherDto {
	private List<WeatherDto> list;
}
