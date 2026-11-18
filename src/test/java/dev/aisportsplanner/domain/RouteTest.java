package dev.aisportsplanner.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RouteTest {

    @Test
    void shouldRequireAtLeastTwoCoordinates() {
        Coordinate onlyPoint = new Coordinate(0.0, 0.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Route(List.of(onlyPoint))
        );
    }

    @Test
    void shouldCalculateDistanceAlongRouteSegments() {
        Coordinate start = new Coordinate(0.0, 0.0);
        Coordinate nearbyPoint = new Coordinate(0.0, 0.0001);

        Route route = new Route(List.of(start, nearbyPoint, start));

        assertEquals(0.02224, route.distanceKm(), 0.00002);
    }
}