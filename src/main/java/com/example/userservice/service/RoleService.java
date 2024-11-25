package com.example.userservice.service;

import com.example.userservice.dto.RoleDto;
import com.example.userservice.utils.ResponsePayload;

import java.util.List;

public interface RoleService {
    ResponsePayload<RoleDto> saveRole(RoleDto roleDto);

    ResponsePayload<RoleDto> findByName(String name);

    ResponsePayload<List<RoleDto>> findAllRoles();
}
