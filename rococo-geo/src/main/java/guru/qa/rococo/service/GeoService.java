package guru.qa.rococo.service;

import guru.qa.rococo.data.CountryEntity;
import guru.qa.rococo.data.GeoEntity;
import guru.qa.rococo.data.repository.CountryRepository;
import guru.qa.rococo.data.repository.GeoRepository;
import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.model.GeoJson;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class GeoService {

    private static final Logger LOG = LoggerFactory.getLogger(GeoService.class);

    private final CountryRepository countryRepository;
    private final GeoRepository geoRepository;

    @Autowired
    public GeoService(CountryRepository countryRepository, GeoRepository geoRepository) {
        this.countryRepository = countryRepository;
        this.geoRepository = geoRepository;
    }

    public @Nonnull
    Page<CountryJson> allCountry(Pageable pageable) {
        List<CountryJson> countries = new ArrayList<>();
        Page<CountryEntity> countryEntities = countryRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())
        );
        for (CountryEntity country : countryEntities) {
            countries.add(CountryJson.fromEntity(country));
        }

        return new PageImpl<>(countries, countryEntities.getPageable(), countryEntities.getTotalElements());
    }

    public @Nonnull
    CountryJson getCountry(UUID uuid) {
        return CountryJson.fromEntity(countryRepository.getById(uuid));
    }

    public @Nonnull
    GeoJson getGeoById(UUID uuid) {
        GeoEntity geoEntity = geoRepository.getGeoById(uuid);

        return GeoJson.fromEntity(geoEntity);
    }

    public @Nonnull
    GeoJson addGeo(GeoJson geoJson) {
        GeoEntity geoEntity = GeoEntity.fromJson(geoJson);
        GeoEntity saved = geoRepository.save(geoEntity);

        return GeoJson.fromEntity(saved);
    }

    public List<GeoJson> getAllGeo() {
        List<GeoJson> geoJsons = new ArrayList<>();
        List<GeoEntity> entities = geoRepository.findAll();
        for(GeoEntity entity : entities) {
            geoJsons.add(GeoJson.fromEntity(entity));
        }

        return geoJsons;
    }

    public GeoJson getGeoByMuseumId(UUID uuid) {
        GeoEntity geoEntity = geoRepository.getGeoEntityByMuseumId(uuid);

        return GeoJson.fromEntity(geoEntity);
    }

    public GeoJson editGeo(GeoJson geoJson) {
        GeoEntity geoEntity = geoRepository.getGeoEntityByMuseumId(geoJson.getMuseumId());
        geoEntity.setCity(geoJson.getCity());
        geoEntity.setMuseumId(geoJson.getMuseumId());
        geoEntity.setCountryEntity(CountryEntity.fromJson(geoJson.getCountryJson()));
        GeoEntity savedGeo = geoRepository.save(geoEntity);

        return GeoJson.fromEntity(savedGeo);
    }
}
