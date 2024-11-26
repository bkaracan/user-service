package com.example.userservice.controller;

import com.example.userservice.dto.RoleDto;
import com.example.userservice.service.RoleService;
import com.example.userservice.utils.ResponsePayload;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleController {

  private final RoleService roleService;

  // Yeni bir rol oluştur (Sadece ADMIN erişebilir)
  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponsePayload<RoleDto> createRole(@RequestBody RoleDto roleDto) {
    return roleService.saveRole(roleDto);
  }

  // Tüm rolleri getir (Sadece ADMIN erişebilir)
  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponsePayload<List<RoleDto>> getAllRoles() {
    return roleService.findAllRoles();
  }

  // İsme göre rol getir (Sadece ADMIN erişebilir)
  @GetMapping("/search")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponsePayload<RoleDto> getRoleByName(@RequestParam String name) {
    return roleService.findByName(name);
  }
}
