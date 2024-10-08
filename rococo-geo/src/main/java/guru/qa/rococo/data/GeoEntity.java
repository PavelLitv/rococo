package guru.qa.rococo.data;

import guru.qa.rococo.model.GeoJson;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "geo")
public class GeoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name= "country_id", referencedColumnName = "id")
    private CountryEntity countryEntity;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, name = "museum_id")
    private UUID museumId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String username) {
        this.city = username;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
    }

    public UUID getMuseumId() {
        return museumId;
    }

    public void setMuseumId(UUID museumId) {
        this.museumId = museumId;
    }

    public static GeoEntity fromJson(GeoJson geoJson) {
        GeoEntity geoEntity = new GeoEntity();
        geoEntity.setCity(geoJson.getCity());
        geoEntity.setMuseumId(geoJson.getMuseumId());
        geoEntity.setCountryEntity(CountryEntity.fromJson(geoJson.getCountryJson()));

        return geoEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoEntity that = (GeoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city);
    }
}
