package com.ramsbaby.blogsearch.domain.blog;

import com.ramsbaby.blogsearch.domain.blog.BlogService.Top10KeywordResponse;
import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchRequestDto;
import com.ramsbaby.blogsearch.aspect.BlogSearchRequestCheck;
import com.ramsbaby.blogsearch.common.response.ApiSuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    @GetMapping("")
    @BlogSearchRequestCheck
    public ApiSuccessResponse getBlogs(@RequestBody BlogSearchRequestDto requestDto) {
        return ApiSuccessResponse.success(blogService.getBlogs(requestDto));
    }

    @GetMapping("/top10")
    public List<Top10KeywordResponse> searchTop10() {
        return blogService.getTop10Keyword();
    }
}
