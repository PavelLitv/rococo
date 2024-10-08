package guru.qa.rococo.service;

import guru.qa.rococo.data.MuseumEntity;
import guru.qa.rococo.data.repository.MuseumRepository;
import guru.qa.rococo.model.MuseumJson;
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
import java.util.UUID;

@Component
public class MuseumService {

    private final MuseumRepository museumRepository;

    @Autowired
    public MuseumService(MuseumRepository museumRepository) {
        this.museumRepository = museumRepository;
    }

    public MuseumJson addMuseum(@Nonnull MuseumJson museumJson) {
        MuseumEntity museumEntity = MuseumEntity.fromJson(museumJson);

        return MuseumJson.fromEntity(museumRepository.save(museumEntity));
    }

    public MuseumJson editMuseum(@Nonnull MuseumJson museumJson) {
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

    public MuseumJson getMuseum(@Nonnull UUID uuid) {
        return MuseumJson.fromEntity(museumRepository.getById(uuid));
    }

    public Page<MuseumJson> getMuseums(Pageable pageable) {
        List<MuseumJson> museums = new ArrayList<>();
        Page<MuseumEntity> museumEntities = museumRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())
        );
        for (MuseumEntity museum : museumEntities) {
            museums.add(MuseumJson.fromEntity(museum));
        }

        return new PageImpl<>(museums, museumEntities.getPageable(), museumEntities.getTotalElements());
    }


    public Page<MuseumJson> getMuseumsByTitle(String title, Pageable pageable) {
        List<MuseumJson> museums = new ArrayList<>();
        Page<MuseumEntity> museumEntities = museumRepository.findAllByTitleContains(
                title, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())
        );
        for (MuseumEntity museum : museumEntities) {
            museums.add(MuseumJson.fromEntity(museum));
        }

        return new PageImpl<>(museums, museumEntities.getPageable(), museumEntities.getTotalElements());
    }
}
