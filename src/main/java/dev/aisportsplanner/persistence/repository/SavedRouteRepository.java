package dev.aisportsplanner.persistence.repository;

import dev.aisportsplanner.persistence.entity.SavedRouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// Provides database operations for saved routes
public interface SavedRouteRepository extends JpaRepository<SavedRouteEntity, Long> {
}
