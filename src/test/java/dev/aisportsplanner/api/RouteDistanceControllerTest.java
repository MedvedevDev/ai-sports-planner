package dev.aisportsplanner.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteDistanceController.class)
@Import(dev.aisportsplanner.service.RouteDistanceService.class)
class RouteDistanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnTotalRouteDistanceInKilometers() throws Exception {
        mockMvc.perform(post("/api/routes/distance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "points": [
                                    {"latitude": 0.0, "longitude": 0.0},
                                    {"latitude": 0.0, "longitude": 0.0001},
                                    {"latitude": 0.0, "longitude": 0.0}
                                  ]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().string("0.022239016046706583"));
    }

    @Test
    void shouldRejectRouteWithFewerThanTwoPoints() throws Exception {
        mockMvc.perform(post("/api/routes/distance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "points": [
                                    {"latitude": 0.0, "longitude": 0.0}
                                  ]
                                }
                                """))
                .andExpect(status().isBadRequest());
    }
}