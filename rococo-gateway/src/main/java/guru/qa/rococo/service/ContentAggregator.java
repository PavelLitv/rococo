package guru.qa.rococo.service;

import guru.qa.rococo.model.GeoJson;
import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.service.api.GeoDataClient;
import org.springframework.stereotype.Component;

@Component
public class ContentAggregator {

    private final GeoDataClient geoDataClient;


    public ContentAggregator(GeoDataClient geoDataClient) {
        this.geoDataClient = geoDataClient;
    }

    public MuseumJson enrichMuseum(MuseumJson museumJson) {
        GeoJson geoJson = geoDataClient.getGeoByMuseumId(museumJson.getId());
        museumJson.setGeo(geoJson);

        return museumJson;
    }
}
