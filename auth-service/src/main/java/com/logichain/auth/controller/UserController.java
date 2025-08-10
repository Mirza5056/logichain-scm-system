package com.logichain.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logichain.auth.service.UserService;
import com.logichain.common.dto.AuthRequest;
import com.logichain.common.dto.MessageResponse;
import com.logichain.common.dto.UserDTO;
import com.logichain.common.model.Users;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        try {
            String message = userService.registerUser(request);
            MessageResponse response = new MessageResponse(false, message);
            return ResponseEntity.ok(response);
        } catch (RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PutMapping("/updateUser/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody AuthRequest authRequest) {
        return userService.updateUser(id, authRequest);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/getStaffMembers")
    public ResponseEntity<List<UserDTO>> getAllStaffMember() {
        List<UserDTO> staffs = userService.getStaffMember();
        return ResponseEntity.ok(staffs);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getMembers")
    public ResponseEntity<List<UserDTO>> getAllMember() {
        List<UserDTO> staffs = userService.getManagerMember();
        return ResponseEntity.ok(staffs);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getStaffById/{id}")
    public ResponseEntity<?> getStaffById(@PathVariable Long id) {
        try
        {
            return ResponseEntity.ok(userService.getUserById(id));
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(new MessageResponse(false, r.getMessage()));
        }
    }
}