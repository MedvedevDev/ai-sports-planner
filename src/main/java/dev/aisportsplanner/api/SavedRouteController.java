package dev.aisportsplanner.api;

import dev.aisportsplanner.service.SavedRouteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

// Provides HTTP endpoints for saving routes
@RestController 
@RequestMapping("api/routes")
public class SavedRouteController {
    
    private final SavedRouteService savedRouteService;

    public SavedRouteController(SavedRouteService savedRouteService) {
        this.savedRouteService = savedRouteService;
    }

    // Saves the submitted route and responds with HTTP 201
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SavedRouteResponse save(@Valid @RequestBody SaveRouteRequest request) {
        return savedRouteService.save(request.name(), request.points());
    }

    // Returns all saved routes
    @GetMapping 
    public List<SavedRouteResponse> findAll() {
        return savedRouteService.findAll();
    }

    // Returns one saved route identified by its ID
    @GetMapping("/{id}")
    public SavedRouteResponse findById(@PathVariable Long id) {
        return savedRouteService.findById(id);
    }
}
