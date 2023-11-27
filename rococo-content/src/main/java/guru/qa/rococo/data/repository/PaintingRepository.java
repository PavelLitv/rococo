package guru.qa.rococo.data.repository;

import guru.qa.rococo.data.PaintingEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaintingRepository extends JpaRepository<PaintingEntity, UUID> {

    @Nonnull
    PaintingEntity getById(@Nonnull UUID uuid);

    @Nullable
    Page<PaintingEntity> findAllByArtistId(@Nonnull UUID uuid, Pageable pageable);

    Page<PaintingEntity> findAllByTitleContains(@Nonnull String title, Pageable pageable);
}
