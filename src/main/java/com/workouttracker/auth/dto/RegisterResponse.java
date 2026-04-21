package com.workouttracker.auth.dto;

public record RegisterResponse(
        Long id,
        String email,
        String displayName
) {
}