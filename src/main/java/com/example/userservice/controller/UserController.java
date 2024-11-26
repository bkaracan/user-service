package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.ResponsePayload;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

  private final UserService userService;

  // Kullanıcı oluşturma (Sadece ADMIN erişebilir)
  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponsePayload<UserDto> createUser(@RequestBody UserDto userDto) {
    return userService.saveUser(userDto);
  }

  // Email'e göre kullanıcı getir (Sadece USER ve ADMIN erişebilir)
  @GetMapping("/getUserByEmail")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public ResponsePayload<UserDto> getUserByEmail(@RequestParam String email) {
    return userService.findByEmail(email);
  }

  // Tüm kullanıcıları getir (Sadece ADMIN erişebilir)
  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponsePayload<List<UserDto>> getAllUsers() {
    return userService.getAllUsers();
  }

  // Kullanıcıyı sil (Sadece ADMIN erişebilir)
  @DeleteMapping("/delete")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponsePayload<?> deleteUser(@RequestParam Long id) {
    return userService.deleteUser(id);
  }
}
