package com.logichain.security.util;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    private final String SECRET_KEY = "baeb11f03b403eff6125dd9cfffd8ec1bac969214e8732783ad7c83fc903c8b6";

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .claim("roles", userDetails.getAuthorities().stream()
                     .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    //     return config.getAuthenticationManager();
    // }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        //return extractUsername(token).equals(userDetails.getUsername());
        try
        {
            final String username = extractUsername(token);
            if(!username.equals(userDetails.getUsername())) {
                throw new JwtException("username in token does not match user details.");
            }
            if(isTokenExpired(token)) {
                throw new ExpiredJwtException(null, null,"Token has expired.");
            }
            return true;
        }catch(ExpiredJwtException | MalformedJwtException | io.jsonwebtoken.security.SignatureException | IllegalArgumentException ex){
            throw new RuntimeException("Jwt validation failed :"+ex.getMessage());
        }
    }
}