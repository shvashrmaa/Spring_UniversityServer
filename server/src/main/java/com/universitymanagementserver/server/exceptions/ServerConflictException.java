package com.universitymanagementserver.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ServerConflictException extends RuntimeException{
    public ServerConflictException(String message) {
        super(message);
    }
}
