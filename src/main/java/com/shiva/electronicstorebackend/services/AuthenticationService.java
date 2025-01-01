package com.shiva.electronicstorebackend.services;

public interface AuthenticationService {

	AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
}
