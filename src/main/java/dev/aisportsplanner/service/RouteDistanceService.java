package dev.aisportsplanner.service;

import dev.aisportsplanner.domain.Coordinate;
import dev.aisportsplanner.domain.Route;
import org.springframework.stereotype.Service;
import java.util.List;

@Service 
public class RouteDistanceService {
    
    // Builds a route from its points and returns its total distance in kilometers
    public double calculateDistanceKm(List<Coordinate> points) {
        Route route = new Route(points);
        return  route.distanceKm();
    }
}
