package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.data.MuseumEntity;

import java.util.UUID;

public class MuseumJson {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("photo")
    private String photo;

    public static MuseumJson fromEntity(MuseumEntity entity) {
        MuseumJson museumJson = new MuseumJson();
        museumJson.setId(entity.getId());
        museumJson.setTitle(entity.getTitle());
        museumJson.setDescription(entity.getDescription());
        museumJson.setPhoto(new String(entity.getPhoto()));

        return museumJson;
    }

    public MuseumJson() {
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
