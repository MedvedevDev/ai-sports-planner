package dev.aisportsplanner.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Holds the new name submitted when editing a saved route
public record UpdateRouteNameRequest(
    @NotBlank @Size(max = 100) String name
) {
    
}
