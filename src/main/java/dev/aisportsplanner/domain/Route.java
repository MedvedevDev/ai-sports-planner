package dev.aisportsplanner.domain;

import java.util.List;

// Represents a route as an ordered list of geographic points
public class Route {

    private final List<Coordinate> points;

    // Creates a route with at least two points 
    public Route(List<Coordinate> points) {
        this.points = List.copyOf(points);

        if (this.points.size() < 2) {
            throw new IllegalArgumentException("A route must contain at least two coordinates.");
        }
    }

    // Returns the route's points in travel order
    public List<Coordinate> points() {
        return points;
    }

    // Adds the distances between neighboring points to get the route's total length in kilometers
    public double distanceKm() {
        double totalDistanceKm = 0.0;

        for (int i = 0; i < points.size() - 1; i++) {
            Coordinate currentPoint = points.get(i);
            Coordinate nextPoint = points.get(i + 1);

            totalDistanceKm += currentPoint.distanceToKm(nextPoint);
        }

        return totalDistanceKm;
    }
}