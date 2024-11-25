package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.Role;
import com.example.userservice.entity.User;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public static UserDto toDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setEmail(user.getEmail());
    userDto.setName(user.getName());
    userDto.setRoles(
        user.getRoles() != null
            ? user.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
            : new HashSet<>());
    return userDto;
  }

  public static User toEntity(UserDto userDto, Set<Role> roles) {
    User user = new User();
    user.setId(userDto.getId());
    user.setEmail(userDto.getEmail());
    user.setName(userDto.getName());
    user.setRoles(roles); // Roller dışarıdan sağlanır
    return user;
  }
}
