package com.my.ex.dto.weather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys {
	private int sunrise;
	private int sunset;
}
