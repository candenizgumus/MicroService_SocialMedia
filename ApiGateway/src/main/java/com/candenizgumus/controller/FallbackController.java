package com.candenizgumus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController
{
    @GetMapping("/auth")
    public ResponseEntity<String> getFallbackAuth(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Auth Service şu an hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }

    @GetMapping("/userprofile")
    public ResponseEntity<String> getFallBackUserProfile(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User Service şu an hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }
    @GetMapping("/post")
    public ResponseEntity<String> getFallBackPostProfile(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Post Service şu an hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }
}
