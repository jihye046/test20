package com.my.ex.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
	private String name;
	private long sunrise;
    private long sunset;
}
