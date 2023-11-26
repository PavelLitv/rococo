package guru.qa.rococo.controller;

import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class MuseumController {

    private final MuseumService museumService;

    @Autowired
    public MuseumController(MuseumService museumService) {
        this.museumService = museumService;
    }

    @GetMapping("/museums")
    public Page<MuseumJson> getAllMuseums(Pageable pageable) {
        return museumService.getMuseums(pageable);
    }

    @GetMapping("/museum/{uuid}")
    public MuseumJson getMuseum(@PathVariable("uuid") UUID uuid) {
        return museumService.getMuseum(uuid);
    }

    @PostMapping("/museum")
    @ResponseStatus(HttpStatus.CREATED)
    public MuseumJson addMuseum(@RequestBody MuseumJson museumJson) {
        return museumService.addMuseum(museumJson);
    }

    @PatchMapping("/museum")
    public MuseumJson editMuseum(@RequestBody MuseumJson museumJson) {
        return museumService.editMuseum(museumJson);
    }
}
