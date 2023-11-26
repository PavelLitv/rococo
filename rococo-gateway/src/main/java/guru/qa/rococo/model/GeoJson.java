package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeoJson {

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private CountryJson country;

    @JsonProperty("museum_id")
    private UUID museumId;

    public GeoJson() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CountryJson getCountry() {
        return country;
    }

    public void setCountry(CountryJson country) {
        this.country = country;
    }

    public UUID getMuseumId() {
        return museumId;
    }

    public void setMuseumId(UUID museumId) {
        this.museumId = museumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoJson geoJson)) return false;

        if (!Objects.equals(city, geoJson.city)) return false;
        return Objects.equals(country, geoJson.country);
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
