package com.ramsbaby.blogsearchservice.domain.blog.kakao.dto;

import com.ramsbaby.blogsearchservice.domain.blog.commonDto.BlogSearchRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KBlogSearchRequestDto {

    String query;
    String sort;
    Integer page;
    Integer size;

    public static KBlogSearchRequestDto of(BlogSearchRequestDto requestDto) {
        int pageParam = requestDto.getPage() == null ? 1 : requestDto.getPage();
        int sizeParam = requestDto.getSize() == null ? 10 : requestDto.getSize();

        return KBlogSearchRequestDto.builder()
            .query(requestDto.getQuery())
            .sort(getTransSort(requestDto.getSort()))
            .page(pageParam > 50 ? 50 : requestDto.getPage())
            .size(sizeParam > 50 ? 50 : requestDto.getSize())
            .build();
    }

    public static String getTransSort(String sort) {
        if (sort.equals("accuracy") || sort.equals("recency")) return sort;
        if (sort.equals("sim")) {
            return "accuracy";
        } else {
            return "recency";
        }
    }
}
