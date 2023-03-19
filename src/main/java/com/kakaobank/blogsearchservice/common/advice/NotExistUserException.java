package com.kakaobank.blogsearchservice.common.advice;

import lombok.Getter;

@Getter
public class NotExistUserException extends RuntimeException {

    public NotExistUserException(String message) {
        super(message);
    }
}
