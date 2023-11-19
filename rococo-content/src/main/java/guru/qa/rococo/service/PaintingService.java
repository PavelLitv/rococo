package guru.qa.rococo.service;

import guru.qa.rococo.data.ArtistEntity;
import guru.qa.rococo.data.MuseumEntity;
import guru.qa.rococo.data.PaintingEntity;
import guru.qa.rococo.data.repository.PaintingRepository;
import guru.qa.rococo.model.PaintingJson;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PaintingService {

    private static final Logger LOG = LoggerFactory.getLogger(PaintingService.class);

    private final PaintingRepository paintingRepository;

    @Autowired
    public PaintingService(PaintingRepository paintingRepository) {
        this.paintingRepository = paintingRepository;
    }

    public @Nonnull
    PaintingJson getPaintingById(@Nonnull UUID uuid) {
        PaintingEntity paintingEntity = paintingRepository.getById(uuid);

        return PaintingJson.fromEntity(paintingEntity);
    }

    public @Nonnull
    List<PaintingJson> getAllPaintingByArtist(@Nonnull UUID uuid) {
        List<PaintingJson> paintings = new ArrayList<>();
        List<PaintingEntity> paintingEntities = paintingRepository.findAllByArtistId(uuid);
        for (PaintingEntity entity : paintingEntities) {
            paintings.add(PaintingJson.fromEntity(entity));
        }

        return paintings;
    }

    public @Nonnull
    PaintingJson addPainting(@Nonnull PaintingJson paintingJson) {
        PaintingEntity paintingEntity = new PaintingEntity();
        paintingEntity.setTitle(paintingJson.getTitle());
        paintingEntity.setDescription(paintingJson.getDescription());
        paintingEntity.setContent(paintingJson.getContent().getBytes());
        paintingEntity.setArtist(new ArtistEntity());
        paintingEntity.getArtist().setId(paintingJson.getArtistJson().getId());
        paintingEntity.setMuseum(new MuseumEntity());
        paintingEntity.getMuseum().setId(paintingJson.getMuseumJson().getId());

        return PaintingJson.fromEntity(paintingRepository.save(paintingEntity));
    }

    public @Nonnull
    PaintingJson editPainting(@Nonnull PaintingJson paintingJson) {
        PaintingEntity paintingEntity = paintingRepository.getById(paintingJson.getId());
        paintingEntity.setTitle(paintingJson.getTitle());
        paintingEntity.setDescription(paintingJson.getDescription());
        paintingEntity.setContent(paintingJson.getContent().getBytes());
        paintingEntity.setArtist(ArtistEntity.fromJson(paintingJson.getArtistJson()));
        paintingEntity.setMuseum(MuseumEntity.fromJson(paintingJson.getMuseumJson()));

        return PaintingJson.fromEntity(paintingRepository.save(paintingEntity));
    }
}
