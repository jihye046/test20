package com.my.ex.dto.weather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
	private String main;
	private String description;
	private String icon;
}
