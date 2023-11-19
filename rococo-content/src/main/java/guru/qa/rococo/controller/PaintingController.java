package guru.qa.rococo.controller;

import guru.qa.rococo.model.PaintingJson;
import guru.qa.rococo.service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PaintingController {

    private final PaintingService paintingService;

    @Autowired
    public PaintingController(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @GetMapping("/painting")
    public PaintingJson getPainting(@RequestParam UUID uuid) {
        return paintingService.getPaintingById(uuid);
    }

    @GetMapping("/painting/artist")
    public List<PaintingJson> getAllPaintingByArtist(@RequestParam UUID uuid) {
        return paintingService.getAllPaintingByArtist(uuid);
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
