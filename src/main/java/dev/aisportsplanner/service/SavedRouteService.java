package dev.aisportsplanner.service;

import dev.aisportsplanner.api.SavedRouteResponse;
import dev.aisportsplanner.domain.Route;
import dev.aisportsplanner.persistence.entity.SavedRouteEntity;
import dev.aisportsplanner.persistence.repository.SavedRouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Handles the application logic for saving routes
@Service 
public class SavedRouteService {
    
    private final SavedRouteRepository savedRouteRepository;

    public SavedRouteService(SavedRouteRepository savedRouteRepository) {
        this.savedRouteRepository = savedRouteRepository;
    }

    // Calculates the route distance, saves it, and returns the saved details
    public SavedRouteResponse save(String name, List<dev.aisportsplanner.domain.Coordinate> points) {
        Route route = new Route(points);
        SavedRouteEntity entity = new SavedRouteEntity(name, route);
        SavedRouteEntity savedEntity = savedRouteRepository.save(entity);

        return new SavedRouteResponse(
            savedEntity.getId(),
            savedEntity.getName(),
            savedEntity.getDistanceKm(),
            savedEntity.getRoutePoints()
        );
    }

}
