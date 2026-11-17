package dev.aisportsplanner.domain;

public record Coordinate(double latitude, double longitude) {
    public Coordinate {
       if (!Double.isFinite(latitude) || latitude < -90.0 || latitude > 90)  {
         throw new IllegalArgumentException(
            "Latitude must be finite and between -90 and 90 degrees"
        );
       }

       if(!Double.isFinite(longitude) || longitude < -180.0 || longitude > 180) {
        throw new IllegalArgumentException(
            "Longitude must be finite and between -180 and 180 degrees"
        );
       }
    }
}