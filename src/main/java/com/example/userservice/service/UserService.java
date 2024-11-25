package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.utils.ResponsePayload;

import java.util.List;

public interface UserService {
  ResponsePayload<UserDto> saveUser(UserDto userDto);

  ResponsePayload<UserDto> findByEmail(String email);

  ResponsePayload<List<UserDto>> getAllUsers();

  ResponsePayload<?> deleteUser(Long id);
}
