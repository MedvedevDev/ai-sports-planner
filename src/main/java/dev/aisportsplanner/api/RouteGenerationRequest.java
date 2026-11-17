package dev.aisportsplanner.api;

import jakarta.validation.constraints.*;

public record RouteGenerationRequest(@NotNull @Min(-90) @Max(90) Double latitude,
    @NotNull @Min(-180) @Max(180) Double longitude,
    @NotNull  @Min(1) @Max(200) Double targetDistanceKm) {
 
}
