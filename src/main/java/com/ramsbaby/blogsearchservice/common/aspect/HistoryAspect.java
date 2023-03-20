package com.ramsbaby.blogsearchservice.common.aspect;

import com.ramsbaby.blogsearchservice.domain.blog.Keyword;
import com.ramsbaby.blogsearchservice.domain.blog.KeywordRepository;
import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchRequestDto;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class HistoryAspect {

    private final KeywordRepository keywordRepository;

    @Transactional
    @Around("execution (* com.ramsbaby.blogsearchservice.connector.KakaoApiClient.*(..)) && args(requestDto, ..)")
    public Object saveHistory(ProceedingJoinPoint joinPoint, BlogSearchRequestDto requestDto) throws Throwable {
        Keyword history = Keyword.builder()
            .searchWord(requestDto.getQuery())
            .build();

        keywordRepository.save(history);

        return joinPoint.proceed();
    }
}
