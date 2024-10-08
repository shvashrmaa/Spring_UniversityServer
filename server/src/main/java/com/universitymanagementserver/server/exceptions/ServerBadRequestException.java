package com.universitymanagementserver.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServerBadRequestException extends RuntimeException {
    public ServerBadRequestException(String message) {
        super(message);
    };
}
