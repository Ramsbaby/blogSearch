package com.ramsbaby.blogsearchservice.domain.blog.kakao.dto;

import com.ramsbaby.blogsearchservice.domain.blog.dto.BlogSearchRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KBlogSearchRequestDto {

    public static final String DEFAULT_SORT_VALUE = "accuracy";
    public static final int DEFAULT_PAGE_VALUE = 1;
    public static final int MAX_PAGE_VALUE = 50;
    public static final int DEFAULT_SIZE_VALUE = 10;
    public static final int MAX_SIZE_VALUE = 50;
    public static final String KAKAO_ACCURACY_VALUE = DEFAULT_SORT_VALUE;
    public static final String KAKAO_RECENCY_VALUE = "recency";
    public static final String NAVER_ACCURACY_VALUE = "sim";

    String query;
    String sort;
    Integer page;
    Integer size;

    public static KBlogSearchRequestDto of(BlogSearchRequestDto requestDto) {
        int pageParam = requestDto.getPage() == null ? DEFAULT_PAGE_VALUE : requestDto.getPage();
        int sizeParam = requestDto.getSize() == null ? DEFAULT_SIZE_VALUE : requestDto.getSize();

        return KBlogSearchRequestDto.builder()
            .query(requestDto.getQuery())
            .sort(getTransSort(requestDto.getSort()))
            .page(pageParam > MAX_PAGE_VALUE ? MAX_PAGE_VALUE : requestDto.getPage())
            .size(sizeParam > MAX_SIZE_VALUE ? MAX_SIZE_VALUE : requestDto.getSize())
            .build();
    }

    public static String getTransSort(String sort) {
        if (ObjectUtils.isEmpty(sort)) {
            return DEFAULT_SORT_VALUE;
        }
        if (sort.equals(KAKAO_ACCURACY_VALUE) || sort.equals(KAKAO_RECENCY_VALUE)) {
            return sort;
        }
        if (sort.equals(NAVER_ACCURACY_VALUE)) {
            return KAKAO_ACCURACY_VALUE;
        } else {
            return KAKAO_RECENCY_VALUE;
        }
    }
}
