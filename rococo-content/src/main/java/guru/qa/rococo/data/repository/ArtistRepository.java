package guru.qa.rococo.data.repository;

import guru.qa.rococo.data.ArtistEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistRepository extends JpaRepository<ArtistEntity, UUID> {

    @Nonnull
    ArtistEntity getById(@Nonnull UUID uuid);

    Page<ArtistEntity> findAllByNameContains(@Nonnull String name, Pageable pageable);
}
