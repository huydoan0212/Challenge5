package com.example.challenge5.services.impl;

import com.example.challenge5.dto.request.RoleRequest;
import com.example.challenge5.dto.response.RoleResponse;
import com.example.challenge5.entity.Role;
import com.example.challenge5.exception.CustomException;
import com.example.challenge5.exception.Error;
import com.example.challenge5.repo.RoleRepository;
import com.example.challenge5.services.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;

    @Override
    public List<RoleResponse> getRoles() {
        List<RoleResponse> roles = new ArrayList<>();
        roleRepository.findAll().forEach(role -> {
            roles.add(RoleResponse.builder()
                    .name(role.getName())
                    .description(role.getDescription())
                    .build());
        });
        return roles;
    }

    @Override
    public RoleResponse addRole(RoleRequest roleRequest) {
        roleRepository.save(Role.builder()
                .description(roleRequest.getDescription())
                .name(roleRequest.getName())
                .build());
        return RoleResponse.builder()
                .description(roleRequest.getDescription())
                .name(roleRequest.getName())
                .build();
    }

    @Override
    public RoleResponse updateRole(RoleRequest roleRequest) {
        Role role = roleRepository.findById(roleRequest.getName()).orElseThrow(() -> new CustomException(Error.ROLE_NOT_FOUND));
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        roleRepository.save(role);
        return RoleResponse.builder()
                .description(roleRequest.getDescription())
                .name(roleRequest.getName())
                .build();
    }

    @Override
    public void deleteRole(String name) {
        roleRepository.deleteById(name);
    }
}
