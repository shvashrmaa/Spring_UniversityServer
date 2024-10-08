package com.universitymanagementserver.server.utils;

import com.universitymanagementserver.server.Constant;
import com.universitymanagementserver.server.models.UserModel;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class GenerateTokenKey {
    public static Map<String , String> GenrateJWTToken(UserModel user) {
        long timeStamp = System.currentTimeMillis();
        String Token = Jwts.builder().signWith(Constant.SECRET_KEY)
                .setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp + Constant.EXPIRATION_TIME))
                .claim("userID" , user.getUserId())
                .claim("email" , user.getEmail())
                .claim("name" , user.getName())
                .compact();

        Map<String , String> tokenMap = new HashMap<>();
        tokenMap.put("Token", Token);
        return tokenMap;
    }
}
