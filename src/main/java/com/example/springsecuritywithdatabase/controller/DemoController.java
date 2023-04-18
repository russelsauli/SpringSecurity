package com.example.springsecuritywithdatabase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/demo")
@RestController
public class DemoController {
    
    @GetMapping
    public ResponseEntity<String > sayhello(){

        return ResponseEntity.ok("hellofromsecured");
    }


}
