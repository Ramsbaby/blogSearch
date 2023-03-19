package com.kakaobank.blogsearchservice.common.aspect;

import com.kakaobank.blogsearchservice.common.advice.InvalidParameterException;
import com.kakaobank.blogsearchservice.common.advice.RequiredKeywordException;
import com.kakaobank.blogsearchservice.domain.blog.dto.BlogRequestDto;
import io.netty.util.internal.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BlogSearchRequestCheckAspect {

    @Around(value = "@annotation(blogRequestCheck) && args(requestDto, ..)")
    public Object checkBlogSearchRequest(ProceedingJoinPoint joinPoint, BlogSearchRequestCheck blogRequestCheck, BlogRequestDto requestDto) throws Throwable {
        String keyword = requestDto.getQuery();
        if (StringUtil.isNullOrEmpty(keyword))
            throw new RequiredKeywordException("keyword is required.");

        Integer page = requestDto.getPage();
        Integer size = requestDto.getSize();
        if (verifyPageOrSize(page) || verifyPageOrSize(size))
            throw new InvalidParameterException("parameter validation fail.");

        return joinPoint.proceed();
    }

    private boolean verifyPageOrSize(Integer value) {
        if (value == null)
            return false;

        return value < 1 || value > 50;
    }
}
