package dev.aisportsplanner.api;

import dev.aisportsplanner.domain.Coordinate;

import java.math.BigDecimal;
import java.util.List;

// Holds the saved route details returned to the caller
public record SavedRouteResponse(
    Long id,
    String name,
    BigDecimal distanceKm,
    List<Coordinate> points
) {
    
}
