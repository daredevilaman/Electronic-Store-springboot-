package com.shiva.electronicstorebackend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.shiva.electronicstorebackend.dtos.AuthenticationRequestDto;
import com.shiva.electronicstorebackend.dtos.AuthenticationResponseDto;
import com.shiva.electronicstorebackend.exceptions.BadApiRequestException;
import com.shiva.electronicstorebackend.jwt.JwtHelper;
import com.shiva.electronicstorebackend.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
		// TODO Auto-generated method stub
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUserName(), authenticationRequestDto.getPassword()));
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new BadApiRequestException(e.getMessage());
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDto.getUserName());
		String generatedToken = jwtHelper.generateToken(userDetails);
		return AuthenticationResponseDto.builder()
										.jwtToken(generatedToken)
										.authenticated(true)
										.build();
	}

}
