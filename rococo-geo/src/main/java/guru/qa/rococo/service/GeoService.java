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
    List<CountryJson> allCountry() {
        List<CountryJson> countries = new ArrayList<>();
        for (CountryEntity country : countryRepository.findAll()) {
            countries.add(CountryJson.fromEntity(country));
        }

        return countries;
    }

    public @Nonnull
    CountryJson getCountry(UUID uuid) {
        return CountryJson.fromEntity(countryRepository.getById(uuid));
    }

    public @Nonnull
    GeoJson getGeoById(UUID uuid) {
        return GeoJson.fromEntity(geoRepository.getGeoById(uuid));
    }

    public @Nonnull
    GeoJson addGeo(GeoJson geoJson) {
        GeoEntity geoEntity = GeoEntity.fromJson(geoJson);

        return GeoJson.fromEntity(geoRepository.save(geoEntity));
    }
}
