package com.kakaobank.blogsearchservice.common.advice;

import lombok.Getter;

@Getter
public class NotEnoughPointException extends RuntimeException {

    public NotEnoughPointException(String message) {
        super(message);
    }
}
