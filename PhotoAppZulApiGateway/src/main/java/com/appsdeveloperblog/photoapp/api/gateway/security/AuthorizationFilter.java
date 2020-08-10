package com.appsdeveloperblog.photoapp.api.gateway.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter{
	
	
	Environment environment;

	public AuthorizationFilter(AuthenticationManager authenticationManager,Environment environment) {
		super(authenticationManager);
		this.environment =environment;}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,FilterChain chain) throws IOException,ServletException {
		
		String authorizationHeader = req.getHeader("Authorization");
		
		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
			chain.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
		
		
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {

		String authorizationHeader = req.getHeader("Authorization");
		
		if(authorizationHeader == null) {
			return null;
		}
		
		String token = authorizationHeader.replace("Bearer","");
		
		
		String userId= Jwts.parser()
					   .setSigningKey("et326r462r632r46tr636")
					   .parseClaimsJws(token)
					   .getBody()
					   .getSubject();
		
		if(userId == null) {
			return null;
		}
		return  new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
	}
}
