package com.example.userservice.serviceImpl;

import com.example.userservice.dto.RoleDto;
import com.example.userservice.entity.Role;
import com.example.userservice.enums.MessageEnum;
import com.example.userservice.enums.ResponseEnum;
import com.example.userservice.mapper.RoleMapper;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.service.RoleService;
import com.example.userservice.utils.ResponsePayload;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;

    @Override
    public ResponsePayload<RoleDto> saveRole(RoleDto roleDto) {
        if (roleRepository.existsByName(roleDto.getName())) {
            throw new RuntimeException("Role already exists: " + roleDto.getName());
        }

        Role role = RoleMapper.toEntity(roleDto);
        role = roleRepository.save(role);

        ResponsePayload<RoleDto> response = new ResponsePayload<>();
        response.setResponse(
                ResponseEnum.CREATED.getStatus(),
                MessageEnum.USER_CREATED.getMessage(),
                RoleMapper.toDto(role)
        );
        return response;
    }

    @Override
    public ResponsePayload<RoleDto> findByName(String name) {
        Role role = roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));

        ResponsePayload<RoleDto> response = new ResponsePayload<>();
        response.setResponse(
                ResponseEnum.SUCCESS.getStatus(),
                MessageEnum.USER_FOUND.getMessage(),
                RoleMapper.toDto(role)
        );
        return response;
    }

    @Override
    public ResponsePayload<List<RoleDto>> findAllRoles() {
        List<RoleDto> roles = roleRepository.findAll()
                .stream()
                .map(RoleMapper::toDto)
                .collect(Collectors.toList());

        ResponsePayload<List<RoleDto>> response = new ResponsePayload<>();
        response.setResponse(
                ResponseEnum.SUCCESS.getStatus(),
                MessageEnum.USER_FOUND.getMessage(),
                roles
        );
        return response;
    }
}
