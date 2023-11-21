package com.bonifacio.app.services;

import com.bonifacio.app.entities.Role;
import com.bonifacio.app.entities.User;
import com.bonifacio.app.repositories.IUserRepository;
import com.bonifacio.app.services.dao.LoginRequest;
import com.bonifacio.app.services.dao.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private  final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public String login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        return jwtService.getToken(user);
    }
    public String register(RegisterRequest request){
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.User)
                .build();
        user =userRepository.save(user);

        return jwtService.getToken(user);
    }
}
