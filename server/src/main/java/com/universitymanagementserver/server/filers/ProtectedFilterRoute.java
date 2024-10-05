package com.universitymanagementserver.server.filers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.security.Key;

public class ProtectedFilterRoute extends GenericFilter {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null) {
            String[] authHeaderArr = authHeader.split("Bearer ");
            if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
                String token = authHeaderArr[1];
                try {
                    Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
                    httpRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));
                } catch (Exception e) {
                    httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid or Expired Token");
                }
            } else {
                httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Token must be in Bearer [Token]");
            }
        } else {
            httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Authorization header is required");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
