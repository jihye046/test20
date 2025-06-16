package com.my.ex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TagDto {
	private int tagId;
	
	@JsonProperty("value")
	private String tagName;
}
