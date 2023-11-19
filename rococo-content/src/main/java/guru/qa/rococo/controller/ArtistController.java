package guru.qa.rococo.controller;

import guru.qa.rococo.model.ArtistJson;
import guru.qa.rococo.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    //todo получение всех и пэйджами и получение одного по ид
    @GetMapping("/artists")
    public List<ArtistJson> getAllArtist(@RequestParam String username,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        return artistService.getArtists(username,  from, to);
    }

    @GetMapping("/artist")
    public ArtistJson getArtist(@RequestParam UUID uuid) {
        return artistService.getArtist(uuid);
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
