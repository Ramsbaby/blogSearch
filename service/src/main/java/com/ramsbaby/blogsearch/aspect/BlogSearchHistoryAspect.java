package com.ramsbaby.blogsearch.aspect;

import com.ramsbaby.blogsearch.domain.blog.BlogService;
import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchRequestDto;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class BlogSearchHistoryAspect {

    private final BlogService blogService;

    @Pointcut("execution(* searchBlog*(..))")
    public void searchBlogMethod() {
    }

    @Around("searchBlogMethod() && args(requestDto, ..)")
    public Object saveHistory(ProceedingJoinPoint joinPoint, BlogSearchRequestDto requestDto) throws Throwable {
        try {
            blogService.updateUseCount(requestDto.getQuery());
        } catch (ObjectOptimisticLockingFailureException e) {
            blogService.updateUseCount(requestDto.getQuery());
        }
        return joinPoint.proceed();
    }
}
