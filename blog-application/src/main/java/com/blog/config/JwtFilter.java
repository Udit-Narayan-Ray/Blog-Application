package com.blog.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtBuilder jwtBuilder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		 String username = null;
		 String token = null;

		//get token from HTTP request
		
		String requestToken = request.getHeader("Authorization");

		if (requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			try {

				username = this.jwtBuilder.getUsernameFromToken(token);

			} catch (MalformedJwtException e) {

				System.out.println("Invalid Jwt Token");

			} catch (IllegalArgumentException e) {

				System.out.println("Unable to get Jwt token");

			} catch (ExpiredJwtException e) {

				System.out.println("jwt token expired");

			}
		} else {
			System.out.println("Jwt doesn't begin with bearer");
		}

		// after getting token then validation

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (this.jwtBuilder.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {

				System.out.println("Invalid Jwt token");

			}
		} else {

			System.out.println("Username is null or security context holder is not null or already authenticated");

		}
	
		filterChain.doFilter(request, response);

	}
}
