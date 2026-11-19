package dev.aisportsplanner.api;

import dev.aisportsplanner.service.RouteDistanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/routes")
public class RouteDistanceController {
    
    private final RouteDistanceService routeDistanceService;

    // Link service to controller
    public RouteDistanceController(RouteDistanceService routeDistanceService) {
        this.routeDistanceService = routeDistanceService;
    }

    // Calculates and returns the route distance for the submitted coordinates
    @PostMapping("/distance")
    public double calculateDistance(@Valid @RequestBody RouteDistanceRequest request) {
        return routeDistanceService.calculateDistanceKm(request.points());
    }
}
