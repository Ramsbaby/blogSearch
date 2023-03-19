package com.kakaobank.blogsearchservice.common.advice;

import java.util.List;
import lombok.Getter;
import org.springframework.validation.ObjectError;

@Getter
public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException(String message) {
        super(message);
    }

}
