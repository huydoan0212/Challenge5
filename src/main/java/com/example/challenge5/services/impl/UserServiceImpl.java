package com.example.challenge5.services.impl;

import com.example.challenge5.dto.request.UserCreationRequest;
import com.example.challenge5.dto.request.UserUpdateRequest;
import com.example.challenge5.dto.response.RoleResponse;
import com.example.challenge5.dto.response.UserResponse;
import com.example.challenge5.entity.Role;
import com.example.challenge5.entity.User;
import com.example.challenge5.exception.CustomException;
import com.example.challenge5.exception.Error;
import com.example.challenge5.repo.RoleRepository;
import com.example.challenge5.repo.UserRepository;
import com.example.challenge5.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(Error.USER_NOT_FOUND));
        return UserResponse.builder()
                .username(user.getUsername())
                .dob(user.getDob())
                .roles(toConvertToRoleResponse(user.getRoles()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(UserResponse.builder()
                    .username(user.getUsername())
                    .dob(user.getDob())
                    .roles(toConvertToRoleResponse(user.getRoles()))
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .build());
        }
        return userResponses;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(Error.USER_NOT_FOUND));
        Set<Role> roles = new HashSet<>();
        List<String> roleRequest = request.getRoles();
        for (String role : roleRequest) {
            roles.add(roleRepository.findById(role).orElseThrow(() -> new CustomException(Error.ROLE_NOT_FOUND)));
        }
        user.setRoles(roles);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return UserResponse.builder()
                .username(user.getUsername())
                .dob(user.getDob())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(toConvertToRoleResponse(user.getRoles()))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserResponse deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(Error.USER_NOT_FOUND));
        if (user != null) {
            userRepository.delete(user);
        }
        assert user != null;
        return UserResponse.builder()
                .username(user.getUsername())
                .dob(user.getDob())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(toConvertToRoleResponse(user.getRoles()))
                .build();
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserResponse createUser(UserCreationRequest request) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById("USER").orElseThrow(() -> new CustomException(Error.ROLE_NOT_FOUND)));
        userRepository.save(User.builder()
                .username(request.getUsername())
                .dob(request.getDob())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .roles(roles)
                .password(passwordEncoder.encode(request.getPassword()))
                .build());
        return UserResponse.builder()
                .roles(toConvertToRoleResponse(roles))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dob(request.getDob())
                .username(request.getUsername())
                .build();
    }

    private Set<RoleResponse> toConvertToRoleResponse(Set<Role> roles) {
        Set<RoleResponse> roleResponses = new HashSet<>();
        for (Role role : roles) {
            roleResponses.add(RoleResponse.builder()
                    .name(role.getName())
                    .description(role.getDescription())
                    .build());
        }
        return roleResponses;
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        if (context == null) {
            throw new CustomException(Error.UNAUTHENTICATED);
        }
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new CustomException(Error.USER_NOT_EXISTED));
        return UserResponse.builder()
                .username(user.getUsername())
                .dob(user.getDob())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(toConvertToRoleResponse(user.getRoles()))
                .build();
    }
}
