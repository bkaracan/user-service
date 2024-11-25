package com.example.userservice.serviceImpl;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.Role;
import com.example.userservice.entity.User;
import com.example.userservice.enums.MessageEnum;
import com.example.userservice.enums.ResponseEnum;
import com.example.userservice.exception.CustomException;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.ResponsePayload;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ResponsePayload<Object> implements UserService {

     private final UserRepository userRepository;
     private final RoleRepository roleRepository;

    @Override
    public ResponsePayload<UserDto> saveUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new CustomException("Email already exists: " + userDto.getEmail(), HttpStatus.CONFLICT);
        }

        // Rolleri veritabanından bul
        Set<Role> roles = userDto.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new CustomException("Role not found: " + roleName, HttpStatus.NOT_FOUND)))
                .collect(Collectors.toSet());

        // User entity'sini oluştur ve kaydet
        User user = UserMapper.toEntity(userDto, roles);
        user = userRepository.save(user);

        // Response oluştur
        ResponsePayload<UserDto> response = new ResponsePayload<>();
        response.setResponse(
                ResponseEnum.CREATED.getStatus(),
                MessageEnum.USER_CREATED.getMessage(),
                UserMapper.toDto(user)
        );
        return response;
    }



    @Override
    public ResponsePayload<UserDto> findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(MessageEnum.USER_NOT_FOUND.getMessage()));

        ResponsePayload<UserDto> response = new ResponsePayload<>();
        response.setResponse(
                ResponseEnum.SUCCESS.getStatus(),
                MessageEnum.USER_FOUND.getMessage(),
                UserMapper.toDto(user)
        );
        return response;
    }

    @Override
    public ResponsePayload<List<UserDto>> getAllUsers() {
        List<UserDto> users = userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .toList();

        ResponsePayload<List<UserDto>> response = new ResponsePayload<>();
        response.setResponse(
                ResponseEnum.SUCCESS.getStatus(),
                MessageEnum.USER_FOUND.getMessage(),
                users
        );
        return response;
    }

    @Override
    public ResponsePayload<Void> deleteUser(Long id) {
        userRepository.deleteById(id);

        ResponsePayload<Void> response = new ResponsePayload<>();
        response.setResponse(
                ResponseEnum.SUCCESS.getStatus(),
                MessageEnum.USER_DELETED.getMessage(),
                null
        );
        return response;
    }
}
