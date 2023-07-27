package org.top.ordersapiapp.middleware;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@Order(0)
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(request,response);
        long duration = System.currentTimeMillis();

        // Создание записи лога
        String logMessage =
                String.format("request method: %s, request URI: %s, response status: %d, "+
                                "request processing time: %d ms",
                request.getMethod(), request.getRequestURI(), response.getStatus(), duration-startTime);

        // Запись лога
        logger.info(logMessage);
    }
}