package guru.qa.rococo.controller;

import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.service.api.GeoDataClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private static final Logger LOG = LoggerFactory.getLogger(CountryController.class);
    private final GeoDataClient geoDataClient;

    public CountryController(GeoDataClient geoDataClient) {
        this.geoDataClient = geoDataClient;
    }

    @GetMapping("/api/country")
    public Page<CountryJson> getCountries(Pageable pageable) {
        Page<CountryJson> countries = geoDataClient.getCountries(pageable);

        return countries;
    }
}
