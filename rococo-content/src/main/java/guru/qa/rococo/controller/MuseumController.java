package guru.qa.rococo.controller;

import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class MuseumController {

    private final MuseumService museumService;

    @Autowired
    public MuseumController(MuseumService museumService) {
        this.museumService = museumService;
    }

    //todo получение всех и пэйджами и получение одного по ид
    @GetMapping("/museums")
    public List<MuseumJson> getAllMuseums(@RequestParam String username,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        return museumService.getMuseums(username, from, to);
    }

    @GetMapping("/museum")
    public MuseumJson getMuseum(@RequestParam UUID uuid) {
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
