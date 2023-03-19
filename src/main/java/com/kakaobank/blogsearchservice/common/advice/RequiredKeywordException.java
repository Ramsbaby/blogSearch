package com.kakaobank.blogsearchservice.common.advice;

import lombok.Getter;

@Getter
public class RequiredKeywordException extends RuntimeException {

    public RequiredKeywordException(String message) {
        super(message);
    }
}
