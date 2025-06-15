package com.my.ex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TagDto {
	int tagId;
	
	@JsonProperty("value")
	String tagName;
}
