package com.ramsbaby.blogsearch.api;

import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchRequestDto;
import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchResponseDto;

import java.util.List;

public interface BlogSearchApi {

    List<BlogSearchResponseDto> searchBlog(BlogSearchRequestDto blogSearchRequestDto);
}
