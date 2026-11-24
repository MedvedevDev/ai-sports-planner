package dev.aisportsplanner.api;

import dev.aisportsplanner.domain.Coordinate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

// Holds the name and coordinates sent when saving a route
public record SaveRouteRequest(
    @NotBlank @Size(max = 100) String name,
    @NotNull @Size(min = 2) List<@NotNull Coordinate> points) {
    
}
