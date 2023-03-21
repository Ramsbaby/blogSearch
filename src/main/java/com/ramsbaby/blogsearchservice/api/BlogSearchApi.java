package com.ramsbaby.blogsearchservice.api;

import com.ramsbaby.blogsearchservice.domain.blog.commonDto.BlogSearchRequestDto;
import com.ramsbaby.blogsearchservice.domain.blog.commonDto.BlogSearchResponseDto;
import java.util.List;

public interface BlogSearchApi {
    List<BlogSearchResponseDto> searchBlog(BlogSearchRequestDto blogSearchRequestDto);
}
