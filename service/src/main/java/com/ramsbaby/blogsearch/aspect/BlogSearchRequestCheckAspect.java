package com.ramsbaby.blogsearch.aspect;

import com.ramsbaby.blogsearch.common.advice.InvalidParameterException;
import com.ramsbaby.blogsearch.common.advice.RequiredKeywordException;
import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchRequestDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BlogSearchRequestCheckAspect {

    @Around(value = "@annotation(blogRequestCheck) && args(requestDto, ..)")
    public Object checkBlogSearchRequest(ProceedingJoinPoint joinPoint, BlogSearchRequestCheck blogRequestCheck,
        BlogSearchRequestDto requestDto) throws Throwable {
        String keyword = requestDto.getQuery();
        if (keyword == null || keyword.isBlank()) {
            throw new RequiredKeywordException("keyword is required.");
        }

        Integer page = requestDto.getPage();
        Integer size = requestDto.getSize();
        if (isNotValidPageOrSize(page) || isNotValidPageOrSize(size)) {
            throw new InvalidParameterException("parameter validation fail.");
        }

        return joinPoint.proceed();
    }

    private boolean isNotValidPageOrSize(Integer value) {
        return value == null || value < 1 || value > 50;
    }
}
