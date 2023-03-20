package com.ramsbaby.blogsearchservice.api;

import com.ramsbaby.blogsearchservice.domain.blog.kakao.dto.KakaoBlogSearchRequestDto;
import com.ramsbaby.blogsearchservice.domain.blog.kakao.dto.DocumentDto;
import java.util.List;

public interface BlogSearchApi {
    List<DocumentDto> searchBlog(KakaoBlogSearchRequestDto blogSearchRequestDto);
}
