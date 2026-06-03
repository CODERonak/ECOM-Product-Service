package com.ecom.productservice.client;

import java.util.UUID;

public record UserInfoResponse(
    UUID id,
    String email,
    String fullName,
    String phone
) {}