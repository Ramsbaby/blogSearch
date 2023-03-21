package com.ramsbaby.blogsearchservice.domain.blog;

import com.ramsbaby.blogsearchservice.common.aspect.BlogSearchRequestCheck;
import com.ramsbaby.blogsearchservice.common.response.ApiSuccessResponse;
import com.ramsbaby.blogsearchservice.domain.blog.BlogService.Top10KeywordResponse;
import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ApiSuccessResponse getBlogs(@RequestBody BlogSearchRequestDto requestDto) {
        return ApiSuccessResponse.success(blogService.getBlogs(requestDto));
    }

    @GetMapping("/top10")
    public List<Top10KeywordResponse> searchTop10() {
        return blogService.getTop10Keyword();
    }
}
