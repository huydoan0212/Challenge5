package com.example.challenge5.services;

import com.example.challenge5.dto.request.UserCreationRequest;
import com.example.challenge5.dto.request.UserUpdateRequest;
import com.example.challenge5.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUserById(String id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(String id, UserUpdateRequest request);

    UserResponse deleteUser(String id);

    UserResponse createUser(UserCreationRequest request);

    UserResponse getMyInfo();
}
