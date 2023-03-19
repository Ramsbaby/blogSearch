package com.kakaobank.blogsearchservice.common.advice;

import lombok.Getter;

@Getter
public class BarcodeExistException extends RuntimeException {

    public BarcodeExistException(String message) {
        super(message);
    }
}
