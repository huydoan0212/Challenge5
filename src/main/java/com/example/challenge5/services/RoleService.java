package com.example.challenge5.services;

import com.example.challenge5.dto.request.RoleRequest;
import com.example.challenge5.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getRoles();

    RoleResponse addRole(RoleRequest roleRequest);

    RoleResponse updateRole(RoleRequest roleRequest);

    void deleteRole(String name);
}
