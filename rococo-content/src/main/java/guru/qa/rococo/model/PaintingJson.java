package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.data.PaintingEntity;

import java.util.UUID;

public class PaintingJson {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("content")
    private String content;

    @JsonProperty("artist")
    private ArtistJson artistJson;

    @JsonProperty("museum")
    private MuseumJson museumJson;

    public PaintingJson(){};

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArtistJson getArtistJson() {
        return artistJson;
    }

    public void setArtistJson(ArtistJson artistJson) {
        this.artistJson = artistJson;
    }

    public MuseumJson getMuseumJson() {
        return museumJson;
    }

    public void setMuseumJson(MuseumJson museumJson) {
        this.museumJson = museumJson;
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

    public static PaintingJson fromEntity(PaintingEntity entity) {
        PaintingJson paintingJson = new PaintingJson();
        paintingJson.setId(entity.getId());
        paintingJson.setDescription(entity.getDescription());
        paintingJson.setTitle(entity.getTitle());
        paintingJson.setContent(new String(entity.getContent()));
        paintingJson.setArtistJson(ArtistJson.fromEntity(entity.getArtist()));
        paintingJson.setMuseumJson(MuseumJson.fromEntity(entity.getMuseum()));

        return paintingJson;
    }
}
