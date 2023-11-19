package guru.qa.rococo.controller;

import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.model.GeoJson;
import guru.qa.rococo.service.GeoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class GeoController {

    private static final Logger LOG = LoggerFactory.getLogger(GeoController.class);

    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    //todo pageable
    @GetMapping("/geo/allCountry")
    public List<CountryJson> getAllCountries() {
        return geoService.allCountry();
    }

    @GetMapping("/geo/country")
    public CountryJson getCountry(@RequestParam UUID uuid) {
        return geoService.getCountry(uuid);
    }

    @GetMapping("/geo")
    public GeoJson getGeo(@RequestParam UUID uuid) {
        return geoService.getGeoById(uuid);
    }

    @PostMapping("/geo")
    public GeoJson addGeo(@RequestBody GeoJson geoJson) {
        return geoService.addGeo(geoJson);
    }
}
