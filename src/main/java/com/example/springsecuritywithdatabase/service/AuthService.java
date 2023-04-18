package com.example.springsecuritywithdatabase.service;

import com.example.springsecuritywithdatabase.model.Role;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.springsecuritywithdatabase.config.JwtService;
import com.example.springsecuritywithdatabase.controller.AuthenticationResponse;
import com.example.springsecuritywithdatabase.model.AuthenticationRequest;
import com.example.springsecuritywithdatabase.model.RegisterRequest;
import com.example.springsecuritywithdatabase.model.User;
import com.example.springsecuritywithdatabase.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    
   
   private final  UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

   private final JwtService jwtService;

   private final AuthenticationManager authenticationManager;




    
    public AuthenticationResponse saveEmployee(RegisterRequest reuqest) {

        var user = User.builder()

        .firstname(reuqest.getFirstname())
        .lastname(reuqest.getLastname())
        .email(reuqest.getEmail())
        .passowrd(passwordEncoder.encode(reuqest.getPassword()))
        .role(Role.USER)
        .build();
    
        userRepository.save(user);

        var jwtToken= jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
        
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken= jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
       
    }

    
    

}
