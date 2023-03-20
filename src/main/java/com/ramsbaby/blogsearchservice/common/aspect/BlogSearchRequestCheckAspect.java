package com.ramsbaby.blogsearchservice.common.aspect;

import com.ramsbaby.blogsearchservice.common.advice.InvalidParameterException;
import com.ramsbaby.blogsearchservice.common.advice.RequiredKeywordException;
import com.ramsbaby.blogsearchservice.domain.blog.kakao.dto.KakaoBlogSearchRequestDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BlogSearchRequestCheckAspect {

    @Around(value = "@annotation(blogRequestCheck) && args(requestDto, ..)")
    public Object checkBlogSearchRequest(ProceedingJoinPoint joinPoint, BlogSearchRequestCheck blogRequestCheck,
        KakaoBlogSearchRequestDto requestDto) throws Throwable {
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
