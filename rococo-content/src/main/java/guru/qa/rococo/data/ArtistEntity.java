package guru.qa.rococo.data;

import guru.qa.rococo.model.ArtistJson;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "artist")
public class ArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String biography;

    @Column(columnDefinition = "bytea")
    private byte[] photo;

    public static ArtistEntity fromJson(ArtistJson artistJson) {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setId(artistJson.getId());
        artistEntity.setName(artistJson.getName());
        artistEntity.setBiography(artistJson.getBiography());
        artistEntity.setPhoto(artistJson.getPhoto().getBytes());

        return artistEntity;
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

    public void setName(String username) {
        this.name = username;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String description) {
        this.biography = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistEntity that = (ArtistEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && biography.equals(that.biography);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, biography, biography);
    }
}
