package com.test.lifung.todolist.controller;


import com.test.lifung.todolist.dto.BaseResponse;
import com.test.lifung.todolist.dto.StatusCode;
import com.test.lifung.todolist.exception.BadParameterException;
import com.test.lifung.todolist.exception.NotFoundEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseAPI {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse<Object> handleException(Exception ex) {
        return new BaseResponse<>(StatusCode.SERVER_ERROR, ex);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotFoundEntityException.class)
    public BaseResponse<String> handleNotFoundEntityException(NotFoundEntityException ex) {
        return new BaseResponse<>(StatusCode.NOT_FOUND, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadParameterException.class)
    public BaseResponse<String> handleBadParameterException(BadParameterException ex) {
        return new BaseResponse<>(StatusCode.BAD_PARAMETER, ex.getMessage());
    }
}
