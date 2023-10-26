package com.blog.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.blog.payloads.UserDefinedConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtBuilder {

	// retrieve username from the token
	public String getUsernameFromToken(String token) {

		return getClaimFromToken(token, Claims::getSubject);

	}

	// retrieve the expiration from the token
	public Date getExpirationDateFromToken(String token) {

		return getClaimFromToken(token, Claims::getExpiration);

	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);

	}

	// for retrieving any information from token we need secret key
	private Claims getAllClaimsFromToken(String token) {

		return Jwts.parser().setSigningKey(UserDefinedConstant.SECRET).parseClaimsJws(token).getBody();

	}

	// check if token is expire

	private Boolean isTokenExpired(String token) {

		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());

	}

	// generate the token

	public String generateToken(UserDetails userDetails) {

		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());

	}

	// while creating the token
	// 1. Define the claims of the token, like issuer ,Expiration ,and ID
	// 2. sign the jwt using the HS512/256 algorithm and secret key
	// 3. According to jwt compact serialization
	// 4. compaction of jwt to a url safe String

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + UserDefinedConstant.JWT_VALIDITY))
				.signWith(SignatureAlgorithm.HS256, UserDefinedConstant.SECRET).compact();

	}

	// validate token

	public Boolean validateToken(String token, UserDetails userDetails) {

		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}
}
