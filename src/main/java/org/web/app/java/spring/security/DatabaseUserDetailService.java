package org.web.app.java.spring.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.model.User;
import org.web.app.java.spring.repo.UserRepository;

@Service
public class DatabaseUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Optional<User> user = userRepository.findByUsername(username);
		
		if(user.isPresent()) {
			return new DatabaseUserDetails(user.get());
		} else throw new UsernameNotFoundException("username" + username + " not found");
		
	}
	
	
	
}
