package dev.aisportsplanner.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoordinateTest {
    
    @Test 
    void shouldCreateValidCoordinate() {
        Coordinate coordinate = new Coordinate(53.7, 9.98);
        assertEquals(53.7, coordinate.latitude());
        assertEquals(9.98, coordinate.longitude());
    }

    @Test
    void shouldRejectLatitudeOutsideAllowedRange() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Coordinate(91.0, 9.98)
        );
    }

    @Test
    void shouldRejectLongitudeOutsideAllowedRange() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Coordinate(53.7, 181.0)
    );
    }

    @Test
    void shouldRejectNonFiniteCoordinates() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Coordinate(Double.NaN, 9.98)
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new Coordinate(53.7, Double.POSITIVE_INFINITY)
        );
    }

        @Test
    void shouldRejectNullCoordinateWhenCalculatingDistance() {
        Coordinate point = new Coordinate(53.7, 9.98);

        assertThrows(
                NullPointerException.class,
                () -> point.distanceToKm(null)
        );
    }
}
