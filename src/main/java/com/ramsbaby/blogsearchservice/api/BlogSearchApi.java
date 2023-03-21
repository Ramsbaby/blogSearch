package com.ramsbaby.blogsearchservice.api;

import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchRequestDto;
import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchResponseDto;
import java.util.List;

public interface BlogSearchApi {
    List<BlogSearchResponseDto> searchBlog(BlogSearchRequestDto blogSearchRequestDto);
}
