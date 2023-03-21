package com.ramsbaby.blogsearchservice.domain.blog.commonDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlogSearchRequestDto {
    String query;
    String sort;
    Integer size;
    Integer page;
}
