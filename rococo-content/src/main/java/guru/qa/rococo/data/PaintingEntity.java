package guru.qa.rococo.data;

import guru.qa.rococo.model.ArtistJson;
import guru.qa.rococo.model.PaintingJson;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "painting")
public class PaintingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private byte[] content;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name= "artist_id", referencedColumnName = "id")
    private ArtistEntity artist;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name= "museum_id", referencedColumnName = "id")
    private MuseumEntity museum;


    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    public MuseumEntity getMuseum() {
        return museum;
    }

    public void setMuseum(MuseumEntity museum) {
        this.museum = museum;
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

    public static PaintingEntity fromJson(PaintingJson paintingJson) {
        PaintingEntity paintingEntity = new PaintingEntity();
        paintingEntity.setTitle(paintingJson.getTitle());
        paintingEntity.setDescription(paintingJson.getDescription());
        paintingEntity.setContent(paintingJson.getContent().getBytes());
        paintingEntity.setArtist(ArtistEntity.fromJson(paintingJson.getArtistJson()));
        paintingEntity.setMuseum(MuseumEntity.fromJson(paintingJson.getMuseumJson()));

        return paintingEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaintingEntity that = (PaintingEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }
}
