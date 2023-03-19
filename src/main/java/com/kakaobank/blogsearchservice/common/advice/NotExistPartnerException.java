package com.kakaobank.blogsearchservice.common.advice;

import lombok.Getter;

@Getter
public class NotExistPartnerException extends RuntimeException {

    public NotExistPartnerException(String message) {
        super(message);
    }
}
