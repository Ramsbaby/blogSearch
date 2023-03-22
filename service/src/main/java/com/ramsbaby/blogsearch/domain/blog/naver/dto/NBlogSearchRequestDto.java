package com.ramsbaby.blogsearch.domain.blog.naver.dto;

import com.ramsbaby.blogsearch.domain.blog.dto.BlogSearchRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NBlogSearchRequestDto {

    public static final String DEFAULT_SORT_VALUE = "sim";
    public static final int DEFAULT_PAGE_VALUE = 1;
    public static final int MAX_PAGE_VALUE = 100;
    public static final int DEFAULT_SIZE_VALUE = 10;
    public static final int MAX_SIZE_VALUE = 100;
    public static final String NAVER_ACCURACY_VALUE = DEFAULT_SORT_VALUE;
    public static final String NAVER_RECENCY_VALUE = "date";
    public static final String KAKAO_ACCURACY_VALUE = "accuracy";

    String query;
    String sort;
    Integer start;
    Integer display;

    public static NBlogSearchRequestDto of(BlogSearchRequestDto requestDto) {
        int startParam = requestDto.getPage() == null ? DEFAULT_PAGE_VALUE : requestDto.getPage();
        int displayParam = requestDto.getSize() == null ? DEFAULT_SIZE_VALUE : requestDto.getSize();

        return NBlogSearchRequestDto.builder()
            .query(requestDto.getQuery())
            .sort(getTransSort(requestDto.getSort()))
            .start(startParam > MAX_PAGE_VALUE ? MAX_PAGE_VALUE : requestDto.getPage())
            .display(displayParam > MAX_SIZE_VALUE ? MAX_SIZE_VALUE : requestDto.getSize())
            .build();
    }

    public static String getTransSort(String sort) {
        if (ObjectUtils.isEmpty(sort)) {
            return DEFAULT_SORT_VALUE;
        }
        if (sort.equals(NAVER_ACCURACY_VALUE) || sort.equals(NAVER_RECENCY_VALUE)) {
            return sort;
        }
        if (sort.equals(KAKAO_ACCURACY_VALUE)) {
            return NAVER_ACCURACY_VALUE;
        } else {
            return NAVER_RECENCY_VALUE;
        }
    }
}
