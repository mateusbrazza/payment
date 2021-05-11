package com.payment.config.security;


import com.payment.model.User;
import com.payment.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthTokenFilter extends OncePerRequestFilter {
	
	private JwtProvider jwtProvider;
	private UserRepository repository;

	public JwtAuthTokenFilter(JwtProvider jwtProvider, UserRepository repository) {
		this.jwtProvider = jwtProvider;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		boolean valid = jwtProvider.isTokenValid(token);
		if (valid) {
			authUser(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void authUser(String token) {
		Long idUser = jwtProvider.getIdUser(token);
		User user = repository.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
