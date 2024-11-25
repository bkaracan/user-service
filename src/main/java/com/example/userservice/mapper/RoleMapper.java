package com.example.userservice.mapper;

import com.example.userservice.dto.RoleDto;
import com.example.userservice.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

  public static RoleDto toDto(Role role) {
    RoleDto roleDto = new RoleDto();
    roleDto.setId(role.getId());
    roleDto.setName(role.getName());
    return roleDto;
  }

  public static Role toEntity(RoleDto roleDto) {
    Role role = new Role();
    role.setId(roleDto.getId());
    role.setName(roleDto.getName());
    return role;
  }
}
