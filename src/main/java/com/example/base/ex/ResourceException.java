package com.example.base.ex;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.awt.*;

@Getter
public class ResourceException extends RuntimeException {
    private int status;
    private String error;
    private String clientMessageId;

    public ResourceException(HttpStatus httpStatus, String clientMessageId) {
        super(httpStatus.getReasonPhrase());
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.clientMessageId = clientMessageId;
    }

    public ResourceException (ExceptionCode exceptionCode, String clientMessageId) {
        super(exceptionCode.getError());
        this.status = exceptionCode.getStatus();
        this.error = exceptionCode.getError();
        this.clientMessageId = clientMessageId;
    }

    public ResourceException(int status, String error, String clientMessageId) {
        super(error);
        this.status = status;
        this.error = error;
    }
}
