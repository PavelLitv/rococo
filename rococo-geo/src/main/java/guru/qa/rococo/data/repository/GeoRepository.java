package guru.qa.rococo.data.repository;

import guru.qa.rococo.data.GeoEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GeoRepository extends JpaRepository<GeoEntity, UUID> {

    GeoEntity getGeoById(UUID uuid);

    GeoEntity getGeoEntityByMuseumId(@Nonnull UUID uuid);
}
