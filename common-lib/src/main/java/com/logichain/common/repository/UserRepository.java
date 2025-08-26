package com.logichain.common.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logichain.common.model.ERole;
import com.logichain.common.model.Role;
import com.logichain.common.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findById(Long id);

    List<Users> findByRolesName(ERole name);
    
    Optional<Users> findUserDTOById(Long id);
    //User emailAlreadyExist(String email);
    List<Users> findByRoles(Role role);
    Optional<Users> findByResetToken(String token);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<Users> findByUsername(String username);
}
