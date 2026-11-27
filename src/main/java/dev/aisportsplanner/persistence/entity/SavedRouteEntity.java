package dev.aisportsplanner.persistence.entity;

import dev.aisportsplanner.domain.Coordinate;
import dev.aisportsplanner.domain.Route;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

// Represents a saved route as a row in the database
@Entity 
@Table(name= "saved_routes")
public class SavedRouteEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "distance_km", nullable = false, precision = 10, scale = 6)
    private BigDecimal distanceKm;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "route_path", nullable = false, columnDefinition = "jsonb")
    private List<Coordinate> routePoints;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    // Lets JPA rebuild this entity when it reads a database row
    protected SavedRouteEntity() {}

    // Creates a database entity from a name and a calculated route
    public SavedRouteEntity(String name, Route route) {
        // if (name == null) {
        //     throw new IllegalArgumentException("Route name is required");
        // }
        // String trimmedName = name.trim();
        String checkedName = Objects.requireNonNull(name, "Route name is required").trim();
        if (checkedName.isEmpty() || checkedName.length() > 100) {
                    throw new IllegalArgumentException("Route name must contain 1 to 100 characters");
            }
        
        Route checkedRoute = Objects.requireNonNull(route, "Route is required");
        this.name = checkedName;
        this.distanceKm = BigDecimal.valueOf(checkedRoute.distanceKm());
        this.routePoints = checkedRoute.points();
        
    }

    // Returns the ID assigned by the database
    public Long getId() {
        return id;
    }

    // Returns the saved route name
    public String getName() {
        return name;
    }

    // Returns the saved distance in kilometers
    public BigDecimal getDistanceKm() {
        return distanceKm;
    }

    // Returns the route coordinates in travel order
    public List<Coordinate> getRoutePoints() {
        return routePoints;
    }

    // Returns the creation time after the entity has been loaded from the database
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    // Changes the name of an already saved route
    public void rename(String name) {
        String checkedName = name == null ? "" : name.trim();
        if (checkedName.isEmpty() || checkedName.length() > 100) {
            throw new IllegalArgumentException("Route name must contain 1 to 100 characters");
        }

        this.name = checkedName;
    }
    
}
