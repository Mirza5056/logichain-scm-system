package com.logichain.auth.service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.logichain.common.dto.AuthRequest;
import com.logichain.common.dto.UserDTO;
import com.logichain.common.model.ERole;
import com.logichain.common.model.Role;
import com.logichain.common.model.Users;
import com.logichain.common.repository.RoleRepository;
import com.logichain.common.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailService emailService;

    public String registerUser(AuthRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exits.");
        }

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // Role role = roleRepository.findByName(ERole.USER)
        // .orElseThrow(() -> new RuntimeException("Default role not found."));

        // //user.setRoles(new HashSet<>());
        // user.getRoles().add(role);
        ERole requestedRole;
        try {
            requestedRole = ERole.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role provided.");
        }

        Role role = roleRepository.findByName(requestedRole)
                .orElseThrow(() -> new RuntimeException("Role not found."));

        user.setRoles(Set.of(role));

        userRepository.save(user);
        return "User Registered Successfully.";
    }

    public Users updateUser(Long id, AuthRequest authRequest) {
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found."));
        user.setUsername(authRequest.getUsername());
        user.setEmail(authRequest.getEmail());
        user.setPassword(authRequest.getPassword());
        ERole requestedRole;
        try {
            requestedRole = ERole.valueOf(authRequest.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role provided.");
        }

        Role role = roleRepository.findByName(requestedRole)
                .orElseThrow(() -> new RuntimeException("Role not found."));

        user.setRoles(Set.of(role));
        Users savedUser = userRepository.save(user);
        return savedUser;
    }

    public List<UserDTO> getStaffMember() {
        return userRepository.findByRolesName(ERole.STAFF).stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles()))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        Users user = userRepository.findUserDTOById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(),
                user.getRoles());
    }

    public List<UserDTO> getManagerMember() {
        return userRepository.findByRolesName(ERole.MANAGER).stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles()))
                .collect(Collectors.toList());
    }

    public boolean generateResetToken(String email) {
        Optional<Users> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            Random random = new Random();
            String token = String.valueOf(random.nextInt(900_000) + 100_000);
            user.setResetToken(token);
            user.setTokenExpiry(LocalDateTime.now().plusMinutes(10));
            userRepository.save(user);
            emailService.sendEmail(user.getEmail(), "Reset Your Password",
                    "Your Code is to Reset Password is :" + token);
            return true;
        }
        return false;
    }

    public boolean resetPassword(String token, String newPassword) {
        Optional<Users> userOptional = userRepository.findByResetToken(token);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid token");
        }
        Users user = userOptional.get();
        if (user.getTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired request new one.");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setTokenExpiry(null);
        userRepository.save(user);
        return true;
    }
}