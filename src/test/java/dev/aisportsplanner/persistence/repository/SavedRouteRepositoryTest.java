package dev.aisportsplanner.persistence.repository;

import dev.aisportsplanner.domain.Coordinate;
import dev.aisportsplanner.domain.Route;
import dev.aisportsplanner.persistence.entity.SavedRouteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SavedRouteRepositoryTest {

    @Autowired
    private SavedRouteRepository savedRouteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void savesAndReloadsRouteIncludingCoordinates() {
        Route route = new Route(List.of(
                new Coordinate(0.0, 0.0),
                new Coordinate(0.0, 0.0001),
                new Coordinate(0.0, 0.0)
        ));

        SavedRouteEntity saved = savedRouteRepository.saveAndFlush(
                new SavedRouteEntity("Test loop", route)
        );

        Long savedId = saved.getId();
        entityManager.clear();

        SavedRouteEntity loaded = savedRouteRepository.findById(savedId).orElseThrow();

        assertEquals("Test loop", loaded.getName());
        assertEquals(route.points(), loaded.getRoutePoints());
        assertEquals(route.distanceKm(), loaded.getDistanceKm().doubleValue(), 0.000001);
    }
}