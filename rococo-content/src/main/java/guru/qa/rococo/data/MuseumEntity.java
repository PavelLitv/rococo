package guru.qa.rococo.data;

import guru.qa.rococo.model.MuseumJson;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "museum")
public class MuseumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column()
    private byte[] photo;

    public static MuseumEntity fromJson(MuseumJson museumJson) {
        MuseumEntity museumEntity = new MuseumEntity();
        museumEntity.setTitle(museumJson.getTitle());
        museumEntity.setDescription(museumJson.getDescription());
        museumEntity.setPhoto(museumJson.getPhoto().getBytes());

        return museumEntity;
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

    public void setTitle(String username) {
        this.title = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        MuseumEntity that = (MuseumEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, description);
    }
}
