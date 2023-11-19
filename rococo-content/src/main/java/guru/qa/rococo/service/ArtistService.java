package guru.qa.rococo.service;

import guru.qa.rococo.data.ArtistEntity;
import guru.qa.rococo.data.repository.ArtistRepository;
import guru.qa.rococo.model.ArtistJson;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

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

    public @Nonnull
    List<ArtistJson> getArtists(@Nonnull String username,
                                @Nullable Date dateFrom,
                                @Nullable Date dateTo) {
        return new ArrayList<>(); //todo заглушка
    }

    public ArtistJson getArtist(UUID uuid) {
        return ArtistJson.fromEntity(artistRepository.getById(uuid));
    }
}
