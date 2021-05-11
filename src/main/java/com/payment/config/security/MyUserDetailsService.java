package com.payment.config.security;


import com.payment.model.User;
import com.payment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> usuario = repository.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}

		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
