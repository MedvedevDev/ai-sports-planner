package dev.aisportsplanner.service;

import dev.aisportsplanner.api.SavedRouteResponse;
import dev.aisportsplanner.domain.Route;
import dev.aisportsplanner.persistence.entity.SavedRouteEntity;
import dev.aisportsplanner.persistence.repository.SavedRouteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        return saveRouteResponse(savedEntity);
    }

     // Finds every saved route and converts it to an API response
     public List<SavedRouteResponse> findAll() {
        List<SavedRouteResponse> responses = new ArrayList<>();

        for (SavedRouteEntity entity : savedRouteRepository.findAll()) {
            responses.add(saveRouteResponse(entity));
        }

        return  responses;
     }

     // Finds route by ID and converts it to an API response
     public SavedRouteResponse findById(Long id) {
        SavedRouteEntity entity = savedRouteRepository.findById(id).orElseThrow(() -> new SavedRouteNotFoundException(id));

        return saveRouteResponse(entity);
     }

    // Copies the public route details from a database entity into a response DTO
     private SavedRouteResponse saveRouteResponse(SavedRouteEntity entity){
        return new SavedRouteResponse(
            entity.getId(),
            entity.getName(),
            entity.getDistanceKm(),
            entity.getRoutePoints()
        );
     }

}
