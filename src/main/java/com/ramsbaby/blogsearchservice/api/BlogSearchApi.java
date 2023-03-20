package com.ramsbaby.blogsearchservice.api;

import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchRequestDto;
import com.ramsbaby.blogsearchservice.domain.blog.dto.DocumentDto;
import java.util.List;

public interface BlogSearchApi {
    List<DocumentDto> searchBlog(BlogSearchRequestDto blogSearchRequestDto);
}
