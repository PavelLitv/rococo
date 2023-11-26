package guru.qa.rococo.service.api;

import guru.qa.rococo.model.CustomPageImpl;
import guru.qa.rococo.model.GeoJson;
import guru.qa.rococo.model.MuseumJson;
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
public class ContentDataClient {

    private final RestTemplate restTemplate;
    private final String rococoContentBaseUrl;
    private final GeoDataClient geoDataClient;

    @Autowired
    public ContentDataClient(RestTemplate restTemplate,
                             @Value("${rococo-content.base-uri}") String rococoContentBaseUrl, GeoDataClient geoDataClient) {
        this.restTemplate = restTemplate;
        this.rococoContentBaseUrl = rococoContentBaseUrl;
        this.geoDataClient = geoDataClient;
    }

    public Page<MuseumJson> getMuseums(Pageable pageable) {
        String url = rococoContentBaseUrl + "/museums?size={size}&page={page}";
        ResponseEntity<CustomPageImpl<MuseumJson>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                },
                pageable.getPageSize(), pageable.getPageNumber());

        return responseEntity.getBody();
    }

    public MuseumJson getMuseum(UUID uuid) {
        String url = rococoContentBaseUrl + "/museum/{uuid}";
        MuseumJson museumJson = restTemplate.getForObject(url, MuseumJson.class, uuid);

        return museumJson;
    }

    public MuseumJson addMuseum(MuseumJson museumJson) {
        String url = rococoContentBaseUrl + "/museum";
        MuseumJson addedMuseum = restTemplate.postForObject(url, museumJson, MuseumJson.class);
        GeoJson geoJson = museumJson.getGeo();
        geoJson.setMuseumId(addedMuseum.getId());
        GeoJson addedGeo = geoDataClient.addGeo(geoJson);
        addedMuseum.setGeo(addedGeo);

        return addedMuseum;
    }

    public MuseumJson editMuseum(MuseumJson museumJson) {
        String url = rococoContentBaseUrl + "/museum";
        MuseumJson editedMuseum = restTemplate.patchForObject(url, museumJson, MuseumJson.class);
        GeoJson geoJson = museumJson.getGeo();
        geoJson.setMuseumId(editedMuseum.getId());
        GeoJson editedGeo = geoDataClient.editGeo(geoJson);
        editedMuseum.setGeo(editedGeo);

        return editedMuseum;
    }
}
