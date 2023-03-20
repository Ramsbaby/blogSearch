package com.ramsbaby.blogsearchservice.common.advice;

import lombok.Getter;

@Getter
public class NotExistBarcodeException extends RuntimeException {

    public NotExistBarcodeException(String message) {
        super(message);
    }
}
