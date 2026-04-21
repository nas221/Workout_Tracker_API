package com.workouttracker.auth;

import com.workouttracker.auth.dto.RegisterRequest;
import com.workouttracker.auth.dto.RegisterResponse;
import com.workouttracker.user.User;
import com.workouttracker.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_shouldCreateUser_whenEmailIsNew() {
        RegisterRequest request = new RegisterRequest("  Test@Email.com  ", "plainPassword", "  Alex  ");

        when(userRepository.existsByEmail("test@email.com")).thenReturn(false);
        when(passwordEncoder.encode("plainPassword")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            ReflectionTestUtils.setField(user, "id", 1L);
            return user;
        });

        RegisterResponse response = authService.register(request);
        verify(passwordEncoder).encode("plainPassword");

        assertEquals(1L, response.id());
        assertEquals("test@email.com", response.email());
        assertEquals("Alex", response.displayName());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertEquals("test@email.com", savedUser.getEmail());
        assertEquals("Alex", savedUser.getDisplayName());
        assertEquals("hashedPassword", savedUser.getPasswordHash());
    }
}
