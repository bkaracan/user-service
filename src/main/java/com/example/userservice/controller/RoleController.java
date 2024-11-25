package com.example.userservice.controller;

import com.example.userservice.dto.RoleDto;
import com.example.userservice.service.RoleService;
import com.example.userservice.utils.ResponsePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleController {

  private final RoleService roleService;

  // Yeni bir rol oluştur
  @PostMapping
  public ResponsePayload<RoleDto> createRole(@RequestBody RoleDto roleDto) {
    return roleService.saveRole(roleDto);
  }

  // Tüm rolleri getir
  @GetMapping
  public ResponsePayload<List<RoleDto>> getAllRoles() {
    return roleService.findAllRoles();
  }

  // Role göre rol getir
  @GetMapping("/{name}")
  public ResponsePayload<RoleDto> getRoleByName(@PathVariable String name) {
    return roleService.findByName(name);
  }
}
