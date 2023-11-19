package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.data.GeoEntity;

import java.util.Objects;
import java.util.UUID;

public class GeoJson {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("country")
    private CountryJson countryJson;

    @JsonProperty("city")
    private String city;

    public GeoJson() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CountryJson getCountryJson() {
        return countryJson;
    }

    public void setCountryJson(CountryJson countryJson) {
        this.countryJson = countryJson;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static GeoJson fromEntity(GeoEntity entity) {
        GeoJson geoJson = new GeoJson();
        geoJson.setId(entity.getId());
        geoJson.setCity(entity.getCity());
        geoJson.setCountryJson(CountryJson.fromEntity(entity.getCountryEntity()));

        return geoJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoJson countryJson = (GeoJson) o;
        return Objects.equals(id, countryJson.id) && Objects.equals(city, countryJson.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city);
    }
}
