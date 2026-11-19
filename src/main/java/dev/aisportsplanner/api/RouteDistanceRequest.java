package dev.aisportsplanner.api;

import java.util.List;

import dev.aisportsplanner.domain.Coordinate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Holds the ordered coordinates that make up a route whose distance is requested
public record RouteDistanceRequest(@NotNull @Size(min = 2) List<Coordinate> points) {
} 
