package com.example.challenge5.controller;

import com.example.challenge5.dto.request.UserCreationRequest;
import com.example.challenge5.dto.request.UserUpdateRequest;
import com.example.challenge5.dto.response.ApiResponse;
import com.example.challenge5.dto.response.UserResponse;
import com.example.challenge5.entity.User;
import com.example.challenge5.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/users")
@Tag(name = "User")
public class UserController {
    UserService userService;

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable String id) {
        UserResponse response = userService.getUserById(id);
        return ApiResponse.<UserResponse>builder()
                .message("Get user by id: " + id)
                .responseData(response)
                .status(response != null ? "success" : "failure")
                .timeStamp(LocalDateTime.now())
                .violation(null)
                .build();
    }

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request) {
        UserResponse response = userService.createUser(request);
        return ApiResponse.<UserResponse>builder()
                .message("Create user")
                .violation(null)
                .status(response != null ? "success" : "failure")
                .timeStamp(LocalDateTime.now())
                .responseData(response)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        UserResponse response = userService.updateUser(id, request);
        return ApiResponse.<UserResponse>builder()
                .message("Update user")
                .violation(null)
                .status(response != null ? "success" : "failure")
                .timeStamp(LocalDateTime.now())
                .responseData(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<UserResponse> deleteUser(@PathVariable String id) {
        UserResponse response = userService.deleteUser(id);
        return ApiResponse.<UserResponse>builder()
                .message("Deleted user")
                .violation(null)
                .status(response != null ? "success" : "failure")
                .timeStamp(LocalDateTime.now())
                .responseData(response)
                .build();
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        UserResponse response = userService.getMyInfo();
        return ApiResponse.<UserResponse>builder()
                .message("My info")
                .violation(null)
                .status(response != null ? "success" : "failure")
                .timeStamp(LocalDateTime.now())
                .responseData(response)
                .build();
    }
}
