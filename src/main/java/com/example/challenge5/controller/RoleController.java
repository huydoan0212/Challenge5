package com.example.challenge5.controller;

import com.example.challenge5.dto.request.RoleRequest;
import com.example.challenge5.dto.response.ApiResponse;
import com.example.challenge5.dto.response.ProductResponse;
import com.example.challenge5.dto.response.RoleResponse;
import com.example.challenge5.services.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/role")
@Tag(name = "Role")
public class RoleController {
    RoleService roleService;

    @GetMapping
    public ApiResponse<List<RoleResponse>> getRoles() {
        List<RoleResponse> roleResponses = roleService.getRoles();
        return ApiResponse.<List<RoleResponse>>builder()
                .message("Get all role")
                .responseData(roleResponses)
                .status(roleResponses != null ? "success" : "failure")
                .timeStamp(LocalDateTime.now())
                .violation(null)
                .build();
    }

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        RoleResponse roleResponse = roleService.addRole(roleRequest);
        return ApiResponse.<RoleResponse>builder()
                .message("Create role")
                .responseData(roleResponse)
                .status(roleResponse != null ? "success" : "failure")
                .timeStamp(LocalDateTime.now())
                .violation(null)
                .build();
    }

    @DeleteMapping("/{name}")
    public ApiResponse<Void> deleteRole(@PathVariable("name") String name) {
        roleService.deleteRole(name);
        return ApiResponse.<Void>builder()
                .message("Delete role")
                .status("success")
                .timeStamp(LocalDateTime.now())
                .violation(null)
                .build();
    }


}
