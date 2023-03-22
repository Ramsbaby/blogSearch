package com.ramsbaby.blogsearch.common.advice;

import lombok.Getter;

@Getter
public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException(String message) {
        super(message);
    }

}
