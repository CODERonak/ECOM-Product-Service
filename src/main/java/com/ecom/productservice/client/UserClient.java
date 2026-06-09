package com.ecom.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserClient {

    // Mirrors POST /auth/validate in User Service
    @GetMapping("api/auth/validate")
    Boolean validateToken(@RequestParam("token") String token);

    // Mirrors GET /auth/email in User Service
    @GetMapping("api/auth/email")
    String extractEmailFromToken(@RequestParam("token") String token);
}
