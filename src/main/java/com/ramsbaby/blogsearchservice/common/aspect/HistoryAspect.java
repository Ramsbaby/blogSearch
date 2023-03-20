package com.ramsbaby.blogsearchservice.common.aspect;

import com.ramsbaby.blogsearchservice.domain.blog.common.Keyword;
import com.ramsbaby.blogsearchservice.domain.blog.common.KeywordRepository;
import com.ramsbaby.blogsearchservice.domain.blog.kakao.dto.KakaoBlogSearchRequestDto;
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
    public Object saveHistory(ProceedingJoinPoint joinPoint, KakaoBlogSearchRequestDto requestDto) throws Throwable {
        Keyword history = Keyword.builder()
            .searchWord(requestDto.getQuery())
            .build();

        keywordRepository.save(history);

        return joinPoint.proceed();
    }
}
