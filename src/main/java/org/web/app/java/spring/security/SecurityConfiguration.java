package org.web.app.java.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
		.requestMatchers("/foods/create").hasAuthority("ADMIN")
		.requestMatchers("/ingredients/create").hasAuthority("ADMIN")
		.requestMatchers("/foods/edit/**").hasAuthority("ADMIN")
		.requestMatchers("/ingredients/edit/**").hasAuthority("ADMIN")
		.requestMatchers("/foods/delete/**").hasAuthority("ADMIN")
		.requestMatchers("/ingredients/delete/**").hasAuthority("ADMIN")
		.requestMatchers("/offers/**").hasAuthority("ADMIN")
		.requestMatchers("/user").hasAuthority("USER")
		.requestMatchers("/admin").hasAuthority("ADMIN")
		.requestMatchers("/**").permitAll()
		.and().formLogin()
		.and().logout();
		
		return http.build();
	}


	@Bean
	DatabaseUserDetailService userDetailService() {
		return new DatabaseUserDetailService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider AuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;	
	}
}
