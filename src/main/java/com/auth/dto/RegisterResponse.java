package com.auth.dto;

public record RegisterResponse(
        Long id,
        String email,
        String displayName
) {
}