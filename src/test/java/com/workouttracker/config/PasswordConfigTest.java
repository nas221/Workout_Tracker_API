package com.workouttracker.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordConfigTest {
    private final PasswordConfig passwordConfig = new PasswordConfig();

    @Test
    void encoderMatchesRawPasswordAgainstHash() {
        PasswordEncoder encoder = passwordConfig.passwordEncoder();
        String rawPassword = "MyStringPass1234";
        String hashedPassword = encoder.encode(rawPassword);

        assertThat(hashedPassword).isNotEqualTo(rawPassword);
        assertThat(encoder.matches(rawPassword, hashedPassword)).isTrue();
        assertThat(encoder.matches("wrong-password", hashedPassword)).isFalse();
    }

    @Test
    void encoderProducesDifferentHashesForSameInputBecauseOfSalt() {
        PasswordEncoder encoder = passwordConfig.passwordEncoder();

        String rawPassword = "SameInputEveryTime";
        String hashOne = encoder.encode(rawPassword);
        String hashTwo = encoder.encode(rawPassword);

        assertThat(hashOne).isNotEqualTo(hashTwo);
        assertThat(encoder.matches(rawPassword, hashOne)).isTrue();
        assertThat(encoder.matches(rawPassword, hashTwo)).isTrue();
    }
}