package com.ramsbaby.blogsearchservice.common.aspect;

import com.ramsbaby.blogsearchservice.domain.blog.BlogService;
import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchRequestDto;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class HistoryAspect {

    private final BlogService blogService;

    @Pointcut("execution(* searchBlog*(..))")
    public void searchBlogMethod() {
    }

    @Transactional
    @Around("searchBlogMethod() && args(requestDto, ..)")
    public Object saveHistory(ProceedingJoinPoint joinPoint, BlogSearchRequestDto requestDto) throws Throwable {
        blogService.updateUseCount(requestDto.getQuery());
        return joinPoint.proceed();
    }
}
