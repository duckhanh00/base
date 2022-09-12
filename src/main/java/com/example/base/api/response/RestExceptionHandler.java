package com.example.base.api.response;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    public ApiResponse handleException(EntityNotFoundException e, HttpServletRequest request) {
        log.error(String.format("%s : %s", e.getClass(), e.getMessage(), e));
        return new ApiResponse(HttpStatus.BAD_REQUEST, request.getHeader("clientMessageId"));
    }
}
