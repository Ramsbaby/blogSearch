package com.kakaobank.blogsearchservice.common.advice;

import com.kakaobank.blogsearchservice.common.response.ErrorResponse;
import java.text.ParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorResponse.Builder()
            .setCode(HttpStatus.BAD_REQUEST.value())
            .setMsg(e.getMessage())
            .setFieldErrorData(e.getAllErrors())
            .build();
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorResponse.Builder()
            .setCode(HttpStatus.BAD_REQUEST.value())
            .setMsg(e.getMessage())
            .build();
    }

    @ExceptionHandler(value = InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidParameterException(InvalidParameterException e) {
        return new ErrorResponse.Builder()
            .setCode(HttpStatus.BAD_REQUEST.value())
            .setMsg(e.getMessage())
            .build();
    }

    @ExceptionHandler(value = NotEnoughPointException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotEnoughPointException(NotEnoughPointException e) {
        return new ErrorResponse.Builder()
            .setCode(HttpStatus.BAD_REQUEST.value())
            .setMsg(e.getMessage())
            .build();
    }

    @ExceptionHandler(value = NotExistPartnerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotExistUserException(NotExistPartnerException e) {
        return new ErrorResponse.Builder()
            .setCode(HttpStatus.NOT_FOUND.value())
            .setMsg(e.getMessage())
            .build();
    }

    @ExceptionHandler(value = NotExistUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotExistUserException(NotExistUserException e) {
        return new ErrorResponse.Builder()
            .setCode(HttpStatus.NOT_FOUND.value())
            .setMsg(e.getMessage())
            .build();
    }

    @ExceptionHandler(value = NotExistBarcodeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotExistBarcodeException(NotExistBarcodeException e) {
        return new ErrorResponse.Builder()
            .setCode(HttpStatus.NOT_FOUND.value())
            .setMsg(e.getMessage())
            .build();
    }

    @ExceptionHandler(value = BarcodeExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBarcodeExistException(BarcodeExistException e) {
        return new ErrorResponse.Builder()
            .setCode(HttpStatus.BAD_REQUEST.value())
            .setMsg(e.getMessage())
            .build();
    }

    @ExceptionHandler(value = ParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse parseException(ParseException e) {
        return new ErrorResponse.Builder()
            .setCode(HttpStatus.BAD_REQUEST.value())
            .setMsg(e.getMessage())
            .build();
    }

}
