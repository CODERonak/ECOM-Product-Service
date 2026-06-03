package com.ecom.productservice.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecom.productservice.client.UserClient;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
// OncePerRequestFilter = runs exactly once per HTTP request
public class JWTFilter extends OncePerRequestFilter {

    private final UserClient userClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        // 1. Validate the token
        if (userClient.validateToken(token)) {
            String email = userClient.extractEmailFromToken(token);

            // 2. Create an Authentication object
            // We use an empty list for authorities for now (or add Roles)
            var authToken = new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());

            // 3. Set it in the Context
            SecurityContextHolder.getContext().setAuthentication(authToken);

            // 4. Store email from the controller
            request.setAttribute("email", email);
        }

        filterChain.doFilter(request, response);
    }
}