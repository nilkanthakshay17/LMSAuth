package com.cognizant.app.lms.auth.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	@Value("${token.secret}")
	private String tokenSecret;
	
	
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
    	System.out.println("inside isTokenExpired");
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
    	System.out.println("inside validateToken1");
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    	Jwts.parserBuilder().setSigningKey(getSignedKey()).build().parseClaimsJws(token);
    }
	
	
	
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		
		return createToken(claims,userName);
	}

	private String createToken(Map<String, Object> claims, String userName) {
		// TODO Auto-generated method stub
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignedKey(),SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getSignedKey() {
		byte[] keyBytes = Decoders.BASE64.decode(tokenSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
