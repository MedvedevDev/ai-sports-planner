package dev.aisportsplanner.domain;

public record Coordinate(double latitude, double longitude) {

    private static final double EARTH_RADIUS_KM = 6371.0088;

    /* 
        Class represents a location, validates its latitude and longitude, and calculates straight-line distance to another coordinate
    */
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

    /* distanceToKm(other) is an instance method: 
    call it on one coordinate and pass the other, 
    e.g. hamburg.distanceToKm(berlin). 
    It converts degrees to radians, applies the Haversine formula, 
    and multiplies the resulting angle by Earth’s average radius. 
    boundedHaversine prevents tiny rounding errors from making 
    the square root invalid. */
    
        public double distanceToKm(Coordinate other) {
        double latitude1 = Math.toRadians(latitude);
        double latitude2 = Math.toRadians(other.latitude());
        double latitudeDifference = Math.toRadians(other.latitude() - latitude);
        double longitudeDifference = Math.toRadians(other.longitude() - longitude);

        double sinHalfLatitude = Math.sin(latitudeDifference / 2.0);
        double sinHalfLongitude = Math.sin(longitudeDifference / 2.0);

        double haversine = sinHalfLatitude * sinHalfLatitude
                + Math.cos(latitude1) * Math.cos(latitude2)
                * sinHalfLongitude * sinHalfLongitude;

        double boundedHaversine = Math.min(1.0, haversine);
        double centralAngle = 2.0 * Math.atan2(
                Math.sqrt(boundedHaversine),
                Math.sqrt(1.0 - boundedHaversine)
        );

        return EARTH_RADIUS_KM * centralAngle;
    }
}