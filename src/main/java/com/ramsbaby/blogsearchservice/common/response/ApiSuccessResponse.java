package com.ramsbaby.blogsearchservice.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiSuccessResponse {

    Object result;

    public static ApiSuccessResponse success(Object result) {
        return ApiSuccessResponse.builder()
            .result(result)
            .build();
    }
}
