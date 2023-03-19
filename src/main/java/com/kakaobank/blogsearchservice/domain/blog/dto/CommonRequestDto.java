package com.kakaobank.blogsearchservice.domain.blog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CommonRequestDto {
	private String restApiKey; 
	private String naverId;
}
