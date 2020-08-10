package com.appsdeveloperblog.photoapp.api.users.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appsdeveloperblog.photoapp.api.users.service.UsersService;
import com.appsdeveloperblog.photoapp.api.users.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.ui.model.LoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
	private UsersService usersService;
	private Environment environment;
	
	
	
	public AuthenticationFilter(UsersService usersService ,Environment environment ,AuthenticationManager authenticationManager ) {
		this.usersService = usersService;
		this.environment = environment;
		super.setAuthenticationManager(authenticationManager);
		
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		
		/*
		 * Request object to read user name and password from which Http request and objectmapper will be used

	to map the values of username and password to a log in request model.

	So now are a log in request model we'll have user name and password and we will then pass it on to user

	name and password authentication token object and that object will be passed on to authenticate method

	which is called on the authentication manager object and get it Authentication manager will return that

	authentication manager object and this method get a indication manager comes from Sprint security.
		 */

		try {

			LoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>())

			);

		} catch (IOException e) {

			throw new RuntimeException(e);
		}
		
	}
	
	
	@Override	    
	protected void successfulAuthentication(HttpServletRequest req,	 HttpServletResponse res,FilterChain chain,Authentication auth) throws IOException, ServletException {
		
		String userName = ((User) auth.getPrincipal()).getUsername();
		UserDto userDetails =usersService.getUserDetailsByEmail(userName);
		String token = Jwts.builder().setSubject(userDetails.getUserId())
					.setExpiration(new Date(System.currentTimeMillis() +Long.parseLong(environment.getProperty("token.expiration_time"))))
					.signWith(SignatureAlgorithm.HS512,environment.getProperty("token.secret"))
					.compact();
		
		res.addHeader("token", token);
		res.addHeader("userId", userDetails.getUserId());
		
		
}

}
