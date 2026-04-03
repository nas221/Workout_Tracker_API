package com.workouttracker.health;

import com.workouttracker.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HealthController.class)
@Import(SecurityConfig.class)
@ActiveProfiles("test")
class HealthControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthEndpointReturnsExpectedFields() throws Exception {
        String timestamp = mockMvc.perform(get("/api/v1/health").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.service").value("workout-tracker-api"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Keep one parsing assertion so we verify timestamp is a valid ISO offset datetime.
        String extracted = timestamp.replaceAll(".*\\\"timestamp\\\":\\\"([^\\\"]+)\\\".*", "$1");
        assertThat(OffsetDateTime.parse(extracted)).isNotNull();
    }
}
