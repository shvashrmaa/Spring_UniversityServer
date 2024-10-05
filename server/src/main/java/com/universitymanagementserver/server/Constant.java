package com.universitymanagementserver.server;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class Constant {

    public static final long EXPIRATION_TIME = 2 * 60 * 60 * 1000;

    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
}
