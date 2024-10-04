package com.universitymanagementserver.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ServerAuthException extends RuntimeException{

    public ServerAuthException(String exception){
        super(exception);
    }
}
