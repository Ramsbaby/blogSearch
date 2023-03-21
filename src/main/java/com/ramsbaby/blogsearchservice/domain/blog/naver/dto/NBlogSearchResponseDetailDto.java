package com.ramsbaby.blogsearchservice.domain.blog.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NBlogSearchResponseDetailDto {
    String title;
    String description;
    String link;
    String bloggername;
}
