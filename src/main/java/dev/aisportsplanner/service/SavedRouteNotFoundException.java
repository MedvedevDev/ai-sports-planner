package dev.aisportsplanner.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Return 404 when a requested saved route is missing
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SavedRouteNotFoundException extends RuntimeException{
    
    public SavedRouteNotFoundException(Long id) {
        super("Saved route with ID " + id + " was not found");
    }
}
