package com.universitymanagementserver.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class ServerNotModifyException extends RuntimeException {
    public ServerNotModifyException(String message) {
        super(message);
    }
}
