package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.config.JwtBuilder;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.LoginAuth;

@RestController
@RequestMapping("/logins")
public class LoginAuthController {

	@Autowired
	private JwtBuilder jwtBuilder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/jwt")
	public ResponseEntity<ApiResponse> loginAuth(@RequestBody LoginAuth loginAuth) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginAuth.getUsername(), loginAuth.getPassword());
		
		try {
			
			this.authenticationManager.authenticate(authenticationToken);
			
		} catch (BadCredentialsException e) {
			
			throw new UsernameNotFoundException("!! Not a valid User !!");
			
		}
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginAuth.getUsername());
		String token = this.jwtBuilder.generateToken(userDetails);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Token : " + token+" ", true), HttpStatus.OK);
	}
}
