package guru.qa.rococo.controller;


import guru.qa.rococo.model.ArtistJson;
import guru.qa.rococo.service.api.ContentDataClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    private static final Logger LOG = LoggerFactory.getLogger(ArtistController.class);

    private final ContentDataClient contentDataClient;

    @Autowired
    public ArtistController(ContentDataClient contentDataClient) {
        this.contentDataClient = contentDataClient;
    }

    @GetMapping
    public Page<ArtistJson> getArtists(Pageable pageable) {
        Page<ArtistJson> artists = contentDataClient.getArtists(pageable);

        return artists;
    }

    @GetMapping(params = {"name"})
    public Page<ArtistJson> filterArtistsByName(@RequestParam(value = "name") String name) {
        Page<ArtistJson> artists = contentDataClient.filterArtistByName(name);

        return artists;
    }

    @GetMapping("/{uuid}")
    public ArtistJson getArtist(@PathVariable("uuid") UUID uuid) {
        ArtistJson artistJson = contentDataClient.getArtist(uuid);

        return artistJson;
    }

    @PostMapping
    public ArtistJson addArtist(@RequestBody ArtistJson artistJson) {
        ArtistJson createdArtist = contentDataClient.addArtist(artistJson);

        return createdArtist;
    }

    @PatchMapping
    public ArtistJson editArtist(@RequestBody ArtistJson artistJson) {
        ArtistJson editedArtist = contentDataClient.editArtist(artistJson);

        return editedArtist;
    }
}
