package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaintingJson {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("content")
    private String content;

    @JsonProperty("museum")
    private MuseumJson museumJson;

    @JsonProperty("artist")
    private ArtistJson artistJson;

    public PaintingJson() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MuseumJson getMuseumJson() {
        return museumJson;
    }

    public void setMuseumJson(MuseumJson museumJson) {
        this.museumJson = museumJson;
    }

    public ArtistJson getArtistJson() {
        return artistJson;
    }

    public void setArtistJson(ArtistJson artistJson) {
        this.artistJson = artistJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaintingJson json)) return false;

        if (!Objects.equals(id, json.id)) return false;
        if (!Objects.equals(title, json.title)) return false;
        if (!Objects.equals(description, json.description)) return false;
        if (!Objects.equals(content, json.content)) return false;
        if (!Objects.equals(museumJson, json.museumJson)) return false;
        return Objects.equals(artistJson, json.artistJson);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (museumJson != null ? museumJson.hashCode() : 0);
        result = 31 * result + (artistJson != null ? artistJson.hashCode() : 0);
        return result;
    }
}
