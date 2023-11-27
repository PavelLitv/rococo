package guru.qa.rococo.controller;

import guru.qa.rococo.model.PaintingJson;
import guru.qa.rococo.service.api.ContentDataClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/painting")
public class PaintingController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final ContentDataClient contentDataClient;

    public PaintingController(ContentDataClient contentDataClient) {
        this.contentDataClient = contentDataClient;
    }

    @GetMapping("/{uuid}")
    public PaintingJson getPainting(@PathVariable("uuid") UUID uuid) {
        PaintingJson paintingJson = contentDataClient.getPainting(uuid);

        return paintingJson;
    }

    @GetMapping("/author/{uuid}")
    public Page<PaintingJson> getPaintingsByArtist(@PathVariable("uuid") UUID uuid, Pageable pageable) {
        Page<PaintingJson> paintingJsons = contentDataClient.getPaintingsByArtist(uuid, pageable);

        return paintingJsons;
    }

    @GetMapping
    public Page<PaintingJson> getAllPaintings(Pageable pageable) {
        Page<PaintingJson> paintingJsons = contentDataClient.getAllPaintings(pageable);

        return paintingJsons;
    }

    @GetMapping(params = {"title"})
    public Page<PaintingJson> filterArtistsByTitle(@RequestParam(value = "title") String title) {
        Page<PaintingJson> paintings = contentDataClient.filterPaintingsByTitle(title);

        return paintings;
    }

    @PostMapping
    public PaintingJson AddPainting(@RequestBody PaintingJson paintingJson) {
        PaintingJson createdPainting = contentDataClient.addPainting(paintingJson);

        return createdPainting;
    }
}
