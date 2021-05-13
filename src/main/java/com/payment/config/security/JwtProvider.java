package com.payment.config.security;

import com.payment.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {
	
	@Value("${payment.app.jwtExpiration}")
	private String expiration;
	
	@Value("${payment.app.jwtSecret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		User logado = (User) authentication.getPrincipal();
		Date date = new Date();
		Date expiredDate = new Date(date.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API CASE PAYMENT")
				.setSubject(logado.getId().toString())
				.setIssuedAt(date)
				.setExpiration(expiredDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public Long getIdUser(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
