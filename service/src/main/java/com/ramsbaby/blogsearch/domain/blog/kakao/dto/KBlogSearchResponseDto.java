package com.ramsbaby.blogsearch.domain.blog.kakao.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KBlogSearchResponseDto {

    List<KBlogSearchResponseDetailDto> documents;
}
