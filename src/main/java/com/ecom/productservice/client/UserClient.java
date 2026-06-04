package com.ecom.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserClient {

    // Mirrors POST /users/validate in User Service
    @PostMapping("api/auth/validate")
    Boolean validateToken(@RequestParam("token") String token);

    // Mirrors GET /users/email in User Service
    @GetMapping("api/auth/email")
    String extractEmailFromToken(@RequestParam("token") String token);
}
