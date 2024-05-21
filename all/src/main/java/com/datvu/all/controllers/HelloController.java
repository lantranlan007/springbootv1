package com.datvu.all.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
    
    @GetMapping("/secure-hello")
    public String secureSayHello() {
        return "Hello, World!";
    }
    
    @GetMapping("/secure-hello1")
    public ResponseEntity<String> secureSayHello1() {
        return ResponseEntity.ok("Hello World!111");
    }
}