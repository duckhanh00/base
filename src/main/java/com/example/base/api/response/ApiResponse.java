package com.example.base.api.response;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse extends ResponseEntity<ApiResponse.Payload> {

    public ApiResponse(HttpStatus httpStatus, String clientMessageId) {
        super(new Payload(httpStatus.value(), httpStatus.getReasonPhrase(), clientMessageId, null), httpStatus.OK);
    }

    public ApiResponse(HttpStatus httpStatus, String clientMessageId, Object data) {
        super(new Payload(httpStatus.value(), httpStatus.getReasonPhrase(), clientMessageId, data), httpStatus.OK);
    }

    @Value
    @AllArgsConstructor
    public static class Payload {
        private int status;
        private String error;
        private String clientMessageId;
        private Object data;
    }
}
