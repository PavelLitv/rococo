package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.data.CountryEntity;

import java.util.Objects;
import java.util.UUID;

public class CountryJson {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("name")
    private String name;

    public CountryJson() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CountryJson fromEntity(CountryEntity entity) {
        CountryJson countryJson = new CountryJson();
        countryJson.setId(entity.getId());
        countryJson.setName(entity.getName());

        return countryJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryJson countryJson = (CountryJson) o;
        return Objects.equals(id, countryJson.id) && Objects.equals(name, countryJson.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
