package com.workouttracker.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordConfigTest{
    private final PasswordConfig passwordConfig = new PasswordConfig();
    @Test
    void encoderMatchesRawPasswordAgainstHash(){
        PasswordEncoder encoder = passwordConfig.passwordEncoder();
        String rawPassword = "MyStringPass1234";
    }



}