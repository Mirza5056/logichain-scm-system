package com.logichain.common.dto;

import java.util.*;

import com.logichain.common.model.ERole;
import com.logichain.common.model.Role;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;

    public UserDTO(Long id, String username, String email, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
