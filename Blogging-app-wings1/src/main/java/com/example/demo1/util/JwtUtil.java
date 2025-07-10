package com.example.demo1.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo1.model.Client;
import com.example.demo1.repo.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	@Value("${jwt.secret}")
	private  String SECRET_KEY;

	
	@Autowired
	private UserRepo repo;
	public Key getSigningKey() {
//		byte[] codes=Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	public String createToken(Map<String,Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username)
				.setIssuedAt(new Date())
				 .setExpiration(new Date(System.currentTimeMillis()+1000*70*70))
				 .signWith(getSigningKey())  .compact();
		
	}
	public String generateToken(String username) {
		Client c=this.repo.findByEmail(username);		
		Map<String,Object> claims=new HashMap<>();
		claims.put("roles",c.getRoles());
		return createToken(claims,username);
	}
	
	public String extractRoleFromToken(String token) {
		return extractAllClaims(token).get("roles",String.class);
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}
	public boolean isExpiredToken(String token) {
		return extractAllClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
	}
	public boolean isValidateToken(String token) {
		return !isExpiredToken(token);
	}

}
