package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.data.ArtistEntity;

import java.util.Arrays;
import java.util.UUID;

public class ArtistJson {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("biography")
    private String biography;

    @JsonProperty("photo")
    private String photo;

    public ArtistJson() {
    }

    public static ArtistJson fromEntity(ArtistEntity entity) {
        ArtistJson artistJson = new ArtistJson();
        artistJson.setId(entity.getId());
        artistJson.setName(entity.getName());
        artistJson.setBiography(entity.getBiography());
        artistJson.setPhoto(new String(entity.getPhoto()));

        return artistJson;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
