package guru.qa.rococo.service;

import guru.qa.rococo.data.ArtistEntity;
import guru.qa.rococo.data.repository.ArtistRepository;
import guru.qa.rococo.model.ArtistJson;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public @Nonnull
    ArtistJson createArtist(@Nonnull ArtistJson artistJson) {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName(artistJson.getName());
        artistEntity.setBiography(artistJson.getBiography());
        artistEntity.setPhoto(artistJson.getPhoto().getBytes());

        return ArtistJson.fromEntity(artistRepository.save(artistEntity));
    }

    public @Nonnull
    ArtistJson editArtist(@Nonnull ArtistJson artistJson) {
        Optional<ArtistEntity> artistById = artistRepository.findById(artistJson.getId());
        if (artistById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can`t find museumJson by given id: " + artistJson.getId());
        } else {
            ArtistEntity artistEntity = artistById.get();
            artistEntity.setName(artistJson.getName());
            artistEntity.setBiography(artistJson.getBiography());
            artistEntity.setPhoto(artistJson.getPhoto().getBytes());
            return ArtistJson.fromEntity(artistRepository.save(artistEntity));
        }
    }

    public Page<ArtistJson> getArtists(Pageable pageable) {
        List<ArtistJson> artists = new ArrayList<>();
        Page<ArtistEntity> artistEntities = artistRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())
        );
        for (ArtistEntity artistEntity : artistEntities) {
            artists.add(ArtistJson.fromEntity(artistEntity));
        }

        return new PageImpl<>(artists, artistEntities.getPageable(), artistEntities.getTotalElements());
    }

    public ArtistJson getArtist(UUID uuid) {
        return ArtistJson.fromEntity(artistRepository.getById(uuid));
    }

    public Page<ArtistJson> getArtistsByName(String name, Pageable pageable) {
        List<ArtistJson> artists = new ArrayList<>();
        Page<ArtistEntity> artistEntities = artistRepository.findAllByNameContains(name, pageable);
        for (ArtistEntity entity : artistEntities) {
            artists.add(ArtistJson.fromEntity(entity));
        }

        return new PageImpl<>(artists, artistEntities.getPageable(), artistEntities.getTotalElements());
    }
}
