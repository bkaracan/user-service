package com.example.userservice.config;

import com.example.userservice.dto.RoleDto;
import com.example.userservice.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CommandLineRunner initRoles(RoleService roleService) {
        return args -> {
            roleService.saveRole(new RoleDto(null, "USER"));
            roleService.saveRole(new RoleDto(null, "ADMIN"));
            System.out.println("Default roles added to the database.");
        };
    }
}
