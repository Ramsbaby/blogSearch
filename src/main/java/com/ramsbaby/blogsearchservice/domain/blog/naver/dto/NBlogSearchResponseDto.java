package com.ramsbaby.blogsearchservice.domain.blog.naver.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NBlogSearchResponseDto {

    Integer total;
    Integer start;
    Integer display;
    List<NBlogSearchResponseDetailDto> items;
}
