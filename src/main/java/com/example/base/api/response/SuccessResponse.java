package com.example.base.api.response;

import org.springframework.http.HttpStatus;

public class SuccessResponse extends ApiResponse{

    public SuccessResponse(String clientMessageId) {
        super(HttpStatus.OK, clientMessageId);
    }

    public  SuccessResponse(String clientMessageId, Object data) {
        super(HttpStatus.OK, clientMessageId, data);
    }
}
