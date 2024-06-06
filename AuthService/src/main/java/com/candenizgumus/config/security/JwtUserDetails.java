package com.candenizgumus.config.security;

import com.candenizgumus.entities.Auth;

import com.candenizgumus.services.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService
{
    private final AuthService authService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return null;
    }


    public UserDetails loadAuthById(Long authid)
    {
        Auth auth = authService.findById(authid);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(auth.getRole().name()));

        return User.builder()
                .username(auth.getUsername())
                .password("")
                .authorities(authorities)
                .build();
    }
}
