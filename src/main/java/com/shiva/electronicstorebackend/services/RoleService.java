package com.shiva.electronicstorebackend.services;

import com.shashwat.electronicstorebackend.dtos.RoleDto;

public interface RoleService {

	RoleDto getNormalRole();
	RoleDto getAdminRole();
}
