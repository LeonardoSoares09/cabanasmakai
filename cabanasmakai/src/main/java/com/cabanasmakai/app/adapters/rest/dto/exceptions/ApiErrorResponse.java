package com.cabanasmakai.app.adapters.rest.dto.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {

    private String message;
    private int status;

    public ApiErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
