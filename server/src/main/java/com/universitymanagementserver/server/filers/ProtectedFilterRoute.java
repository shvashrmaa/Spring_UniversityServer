package com.universitymanagementserver.server.filers;

import com.universitymanagementserver.server.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class ProtectedFilterRoute extends GenericFilter {
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
                    Claims claims = Jwts.parser().setSigningKey(Constant.SECRET_API_KEY).parseClaimsJws(token).getBody();
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
