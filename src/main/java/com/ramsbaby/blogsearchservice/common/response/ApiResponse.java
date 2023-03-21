package com.ramsbaby.blogsearchservice.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ramsbaby.blogsearchservice.domain.blog.commonDto.BlogSearchResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    Integer code;
    String message;
    Object result;

    public static ApiResponse error(Integer code, String message) {
        return ApiResponse.builder()
            .code(code)
            .message(message)
            .build();
    }

    public static ApiResponse success(Object result) {
        return ApiResponse.builder()
            .result(result)
            .build();
    }
}
