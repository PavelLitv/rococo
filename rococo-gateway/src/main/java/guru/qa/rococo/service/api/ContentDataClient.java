package guru.qa.rococo.service.api;

import guru.qa.rococo.model.*;
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
        String url = rococoContentBaseUrl + "/museum?size={size}&page={page}";
        ResponseEntity<CustomPageImpl<MuseumJson>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                },
                pageable.getPageSize(), pageable.getPageNumber());

        return responseEntity.getBody();
    }


    public Page<MuseumJson> filterMuseumsByTitle(String title) {
        String url = rococoContentBaseUrl + "/museum/filter?title={title}";
        ResponseEntity<CustomPageImpl<MuseumJson>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                }, title);

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

    public Page<ArtistJson> getArtists(Pageable pageable) {
        String url = rococoContentBaseUrl + "/artists?size={size}&page={page}";
        ResponseEntity<CustomPageImpl<ArtistJson>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                },
                pageable.getPageSize(), pageable.getPageNumber());

        return responseEntity.getBody();
    }

    public ArtistJson getArtist(UUID uuid) {
        String url = rococoContentBaseUrl + "/artist/{uuid}";
        ArtistJson artistJson = restTemplate.getForObject(url, ArtistJson.class, uuid);

        return artistJson;
    }

    public ArtistJson addArtist(ArtistJson artistJson) {
        String url = rococoContentBaseUrl + "/artist";
        ArtistJson createdArtist = restTemplate.postForObject(url, artistJson, ArtistJson.class);

        return createdArtist;
    }

    public ArtistJson editArtist(ArtistJson artistJson) {
        String url = rococoContentBaseUrl + "/artist";
        ArtistJson editedArtist = restTemplate.patchForObject(url, artistJson, ArtistJson.class);

        return editedArtist;
    }

    public Page<ArtistJson> filterArtistByName(String name) {
        String url = rococoContentBaseUrl + "/artist/filter?name={name}";
        ResponseEntity<CustomPageImpl<ArtistJson>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                },
                name);

        return responseEntity.getBody();
    }

    public Page<PaintingJson> getPaintingsByArtist(UUID uuid, Pageable pageable) {
        String url = rococoContentBaseUrl + "/painting/artist/{uuid}";
        ResponseEntity<CustomPageImpl<PaintingJson>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                },
                uuid, pageable.getPageSize(), pageable.getPageNumber());

        return responseEntity.getBody();
    }

    public PaintingJson addPainting(PaintingJson paintingJson) {
        String url = rococoContentBaseUrl + "/painting";
        PaintingJson createdPainting = restTemplate.postForObject(url, paintingJson, PaintingJson.class);

        return createdPainting;
    }

    public PaintingJson getPainting(UUID uuid) {
        String url = rococoContentBaseUrl + "/painting/{uuid}";
        PaintingJson paintingJson = restTemplate.getForObject(url, PaintingJson.class, uuid);

        return paintingJson;
    }

    public Page<PaintingJson> getAllPaintings(Pageable pageable) {
        String url = rococoContentBaseUrl + "/painting/all";
        ResponseEntity<CustomPageImpl<PaintingJson>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                },
                pageable.getPageSize(), pageable.getPageNumber());

        return responseEntity.getBody();
    }

    public Page<PaintingJson> filterPaintingsByTitle(String title) {
        String url = rococoContentBaseUrl + "/painting/filter?title={title}";
        ResponseEntity<CustomPageImpl<PaintingJson>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                },
                title);

        return responseEntity.getBody();
    }
}
