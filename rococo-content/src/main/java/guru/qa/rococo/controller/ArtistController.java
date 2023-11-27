package guru.qa.rococo.controller;

import guru.qa.rococo.model.ArtistJson;
import guru.qa.rococo.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artists")
    public Page<ArtistJson> getArtists(Pageable pageable) {
        return artistService.getArtists(pageable);
    }

    @GetMapping("/artist/{uuid}")
    public ArtistJson getArtist(@PathVariable("uuid") UUID uuid) {
        return artistService.getArtist(uuid);
    }

    @GetMapping("/artist/filter")
    public Page<ArtistJson> getArtistsByName(@RequestParam String name, Pageable pageable) {
        return artistService.getArtistsByName(name, pageable);
    }

    @PostMapping("/artist")
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistJson addArtist(@RequestBody ArtistJson artistJson) {
        return artistService.createArtist(artistJson);
    }

    @PatchMapping("/artist")
    public ArtistJson editSpend(@RequestBody ArtistJson artistJson) {
        return artistService.editArtist(artistJson);
    }
}
