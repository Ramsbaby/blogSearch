package com.ramsbaby.blogsearch.aspect;

import com.ramsbaby.blogsearch.domain.blog.BlogService;
import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchRequestDto;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.jpa.repository.Lock;
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
        int retryCnt = 0;
        try {
            blogService.updateUseCount(requestDto.getQuery());
        } catch (ObjectOptimisticLockingFailureException e) {
            retryCnt++;
            if (retryCnt == 3) {
                return joinPoint.proceed();
            } else {
                blogService.updateUseCount(requestDto.getQuery());
            }

        }
        return joinPoint.proceed();
    }
}
