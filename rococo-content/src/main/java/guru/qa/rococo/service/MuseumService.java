package guru.qa.rococo.service;

import guru.qa.rococo.data.MuseumEntity;
import guru.qa.rococo.data.repository.MuseumRepository;
import guru.qa.rococo.model.MuseumJson;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class MuseumService {

    private final MuseumRepository museumRepository;

    @Autowired
    public MuseumService(MuseumRepository museumRepository) {
        this.museumRepository = museumRepository;
    }

    public @Nonnull
    MuseumJson addMuseum(@Nonnull MuseumJson museumJson) {
        MuseumEntity museumEntity = MuseumEntity.fromJson(museumJson);

        return MuseumJson.fromEntity(museumRepository.save(museumEntity));
    }

    public @Nonnull
    MuseumJson editMuseum(@Nonnull MuseumJson museumJson) {
        MuseumEntity museumEntity = museumRepository.getById(museumJson.getId());
        if (museumEntity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can`t find museumJson by given id: " + museumJson.getId());
        } else {
            museumEntity.setTitle(museumJson.getTitle());
            museumEntity.setDescription(museumJson.getDescription());
            museumEntity.setPhoto(museumJson.getPhoto().getBytes());

            return MuseumJson.fromEntity(museumRepository.save(museumEntity));
        }
    }

    public @Nonnull
    MuseumJson getMuseum(@Nonnull UUID uuid) {
        return MuseumJson.fromEntity(museumRepository.getById(uuid));
    }

    //todo заглушка
    public List<MuseumJson> getMuseums(String username, Date from, Date to) {

        return null;
    }
}
