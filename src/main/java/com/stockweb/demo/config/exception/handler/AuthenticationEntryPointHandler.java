package com.stockweb.demo.config.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stockweb.demo.config.exception.error.ErrorCode;
import com.stockweb.demo.config.exception.error.ErrorDetails;
import com.stockweb.demo.config.util.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(getBody());
    }

    private static String getBody() throws JsonProcessingException {
        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.BAD_CREDENTIALS)
                .detail(ErrorCode.BAD_CREDENTIALS.getDefaultMessage())
                .build();

        return JsonUtils.objectToJson(error);
    }
}