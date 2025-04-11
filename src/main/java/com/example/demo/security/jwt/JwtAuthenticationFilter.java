package com.example.demo.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    String token = null;

    // Check if the Authorization header is present and starts with "Bearer "
    if (authHeader != null && authHeader.startsWith("Bearer "))
      token = authHeader.substring(7);

    // If the token is not null and valid, set the authentication in the security context
    if (token != null && JwtUtil.isTokenValid(token)) {
      Authentication authentication = new UsernamePasswordAuthenticationToken(
        JwtUtil.getClaims(token).getSubject(), // Username from token
        null, // No credentials needed
        Collections.emptyList() // No authorities needed
      );

      // Set the authentication in the security context
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }
}
