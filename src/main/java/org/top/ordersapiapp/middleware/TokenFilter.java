package org.top.ordersapiapp.middleware;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
@Slf4j
public class TokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestToken = request.getHeader("Token");
        String token = "ApiKey";
        if (requestToken != null && requestToken.equals(token)) {
            log.info("Token value found successfully");
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(403);
            response.getWriter().println("Missing valid token value");
            log.info("Token value not found");
        }
    }
}
