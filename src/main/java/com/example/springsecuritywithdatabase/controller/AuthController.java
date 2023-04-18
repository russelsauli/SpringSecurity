package com.example.springsecuritywithdatabase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springsecuritywithdatabase.model.RegisterRequest;
import com.example.springsecuritywithdatabase.service.AuthService;

import lombok.AllArgsConstructor;

import com.example.springsecuritywithdatabase.model.AuthenticationRequest;



@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody RegisterRequest reuqest){

        return ResponseEntity.ok(authService.saveEmployee(reuqest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>registers(@RequestBody AuthenticationRequest reuqest){

        return ResponseEntity.ok(authService.authenticate(reuqest));
    }
   


    
}
