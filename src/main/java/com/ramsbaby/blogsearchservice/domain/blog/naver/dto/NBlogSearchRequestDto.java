package com.ramsbaby.blogsearchservice.domain.blog.naver.dto;

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
public class NBlogSearchRequestDto {

    public static final String DEFAULT_SORT_VALUE = "sim";
    String query;
    String sort;
    Integer start;
    Integer display;

    public static NBlogSearchRequestDto of(BlogSearchRequestDto requestDto) {
        int startParam = requestDto.getPage() == null ? 1 : requestDto.getPage();
        int displayParam = requestDto.getSize() == null ? 10 : requestDto.getSize();

        return NBlogSearchRequestDto.builder()
            .query(requestDto.getQuery())
            .sort(getTransSort(requestDto.getSort()))
            .start(startParam > 100 ? 100 : requestDto.getPage())
            .display(displayParam > 100 ? 100 : requestDto.getSize())
            .build();
    }

    public static String getTransSort(String sort) {
        if (ObjectUtils.isEmpty(sort)) {
            return DEFAULT_SORT_VALUE;
        }
        if (sort.equals("sim") || sort.equals("date")) {
            return sort;
        }
        if (sort.equals("accuracy")) {
            return "sim";
        } else {
            return "date";
        }
    }
}
