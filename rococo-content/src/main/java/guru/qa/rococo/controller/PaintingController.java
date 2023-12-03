package guru.qa.rococo.controller;

import guru.qa.rococo.model.PaintingJson;
import guru.qa.rococo.service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PaintingController {

    private final PaintingService paintingService;

    @Autowired
    public PaintingController(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @GetMapping("/painting/{uuid}")
    public PaintingJson getPainting(@PathVariable("uuid") UUID uuid) {
        return paintingService.getPaintingById(uuid);
    }

    @GetMapping("/painting/artist/{uuid}")
    public Page<PaintingJson> getAllPaintingByArtist(@PathVariable("uuid") UUID uuid, Pageable pageable) {
        return paintingService.getAllPaintingByArtist(uuid, pageable);
    }

    @GetMapping("/painting/all")
    public Page<PaintingJson> getAllPaintings(Pageable pageable) {
        return paintingService.getAllPaintings(pageable);
    }

    @GetMapping("painting/filter")
    public Page<PaintingJson> getPaintingsByTitle(@RequestParam String title, Pageable pageable) {
        return paintingService.getPaintingsByTitle(title, pageable);
    }

    @PostMapping("/painting")
    public PaintingJson addPainting(@RequestBody PaintingJson paintingJson) {
        return paintingService.addPainting(paintingJson);
    }

    @PatchMapping("/painting")
    public PaintingJson editPainting(@RequestBody PaintingJson paintingJson) {
        return paintingService.editPainting(paintingJson);
    }
}
