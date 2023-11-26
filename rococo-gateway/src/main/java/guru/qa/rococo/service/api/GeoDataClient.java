package guru.qa.rococo.service.api;

import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.model.CustomPageImpl;
import guru.qa.rococo.model.GeoJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class GeoDataClient {

    private final RestTemplate restTemplate;
    private final String rococoGeoBaseUrl;

    @Autowired
    public GeoDataClient(RestTemplate restTemplate,
                         @Value("${rococo-geo.base-uri}") String rococoGeoBaseUrl) {
        this.restTemplate = restTemplate;
        this.rococoGeoBaseUrl = rococoGeoBaseUrl;
    }

    public GeoJson getGeoByMuseumId(UUID uuid) {
        String url = rococoGeoBaseUrl + "/geo/museum/{uuid}";
        GeoJson geoJson = restTemplate.getForObject(url, GeoJson.class, uuid);

        return geoJson;
    }

    public Page<CountryJson> getCountries(Pageable pageable) {
        String url = rococoGeoBaseUrl + "/geo/allCountry?size={size}&page={page}";
        ResponseEntity<CustomPageImpl<CountryJson>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {},
                pageable.getPageSize(), pageable.getPageNumber());

        return responseEntity.getBody();
    }

    public GeoJson addGeo(GeoJson geoJson) {
        String url = rococoGeoBaseUrl + "/geo";
        GeoJson addedGeo = restTemplate.postForObject(url, geoJson, GeoJson.class);

        return addedGeo;
    }

    public GeoJson editGeo(GeoJson geoJson) {
        String url = rococoGeoBaseUrl + "/geo";
        GeoJson editedGeo = restTemplate.patchForObject(url, geoJson, GeoJson.class);
        //todo гавнокод, надо разобраться почему при содании/редактировании geo не возвращается название страны
        editedGeo = getGeoByMuseumId(editedGeo.getMuseumId());

        return editedGeo;
    }
}
