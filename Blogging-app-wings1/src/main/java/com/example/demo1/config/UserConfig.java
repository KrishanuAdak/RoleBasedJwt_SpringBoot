package com.example.demo1.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo1.filter.JwtFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class UserConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private UserImplDetails userDetailsService;
	
	
	@Bean
	public SecurityFilterChain secureHyttps(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests(auth->
		auth.requestMatchers("/api/public/**").permitAll()
		.requestMatchers("/api/auth/manager/**").hasRole("MANAGER")
		.requestMatchers("/api/auth/employee/**").hasRole("EMPLOYEE")
		.anyRequest().authenticated());
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore((Filter) jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
		
		
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider provider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(encoder());
		return provider;
		
		
	}
	
	@Bean
	public AuthenticationManager configAuthentication(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
