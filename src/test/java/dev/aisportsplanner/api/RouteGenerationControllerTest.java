package dev.aisportsplanner.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteGenerationController.class)
class RouteGenerationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnValidRouteRequest() throws Exception {
        mockMvc.perform(post("/api/routes/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "latitude": 53.7,
                                  "longitude": 9.98,
                                  "targetDistanceKm": 5
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.latitude").value(53.7))
                .andExpect(jsonPath("$.longitude").value(9.98))
                .andExpect(jsonPath("$.targetDistanceKm").value(5.0));
    }

    @Test
    void shouldRejectLatitudeOutsideAllowedRange() throws Exception {
        mockMvc.perform(post("/api/routes/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "latitude": 91,
                                  "longitude": 9.98,
                                  "targetDistanceKm": 5
                                }
                                """))
                .andExpect(status().isBadRequest());
    }
}