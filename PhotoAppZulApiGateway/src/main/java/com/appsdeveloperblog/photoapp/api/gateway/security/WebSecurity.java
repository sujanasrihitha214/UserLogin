package com.appsdeveloperblog.photoapp.api.gateway.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	 Environment environment ;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/users-ws/users/login").permitAll()
		.antMatchers(HttpMethod.POST,"/users-ws/users").permitAll()
		.antMatchers("/users-ws/h2-console/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new AuthorizationFilter(authenticationManager(), environment));
		
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//stateless to not to store token in caching.So it will not make any session
		
	}
	
	

}
