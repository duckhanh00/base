package com.example.base.ex;

import com.example.base.service.Translator;
import org.springframework.http.HttpStatus;

public class AppException extends ResourceException {
    public AppException(HttpStatus httpStatus, String clientMessageId) { super(httpStatus, clientMessageId);}

    public AppException(ExceptionCode exceptionCode, String clientMessageId) {
        super(exceptionCode.getStatus(), Translator.toLocale(exceptionCode.getError()), clientMessageId);
    }

    public AppException(ExceptionCode exceptionCode, Object[] args, String clientMessageId) {
        super(exceptionCode.getStatus(), Translator.toLocale(exceptionCode.getError(), args), clientMessageId);
    }
}
