package com.logichain.security.model;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.logichain.common.model.Users;
import com.logichain.common.repository.UserRepository;
import com.logichain.security.util.UserPrincipal;
 
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        // return new org.springframework.security.core.userdetails.User(
        //         user.getEmail(),
        //         user.getPassword(),
        //         user.getRoles().stream()
        //                 .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
        //                 .collect(Collectors.toList()));
        return new  UserPrincipal(user);
    }
}