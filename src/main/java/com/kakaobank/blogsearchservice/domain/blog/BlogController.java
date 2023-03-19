package com.kakaobank.blogsearchservice.domain.blog;

import com.kakaobank.blogsearchservice.common.aspect.BlogSearchRequestCheck;
import com.kakaobank.blogsearchservice.common.response.ApiResponse;
import com.kakaobank.blogsearchservice.domain.blog.BlogService.Top10KeywordResponse;
import com.kakaobank.blogsearchservice.domain.blog.dto.BlogRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    @GetMapping("")
    @BlogSearchRequestCheck
    public ApiResponse getBlogs(@RequestBody BlogRequestDto requestDto) {
        return ApiResponse.success(blogService.getBlogs(requestDto));
    }

    @GetMapping("/top10")
    public List<Top10KeywordResponse> searchTop10() {
        return blogService.getTop10Keyword();
    }
}
