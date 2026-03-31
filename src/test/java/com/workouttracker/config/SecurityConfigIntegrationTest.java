package com.workouttracker.config;

}
    }
                .andExpect(status().isUnauthorized());
        mockMvc.perform(get("/api/v1/workouts"))
    void protectedEndpointRequiresAuthentication() throws Exception {
    @Test

    }
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/health"))
    void publicHealthEndpointIsAccessibleWithoutAuthentication() throws Exception {
    @Test

    private MockMvc mockMvc;
    @Autowired

class SecurityConfigIntegrationTest {
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;

