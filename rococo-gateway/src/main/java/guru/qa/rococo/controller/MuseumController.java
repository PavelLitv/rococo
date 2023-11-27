package guru.qa.rococo.controller;

import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.service.ContentAggregator;
import guru.qa.rococo.service.api.ContentDataClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/museum")
public class MuseumController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final ContentDataClient contentDataClient;
    private final ContentAggregator aggregator;

    @Autowired
    public MuseumController(ContentDataClient contentDataClient, ContentAggregator aggregator) {
        this.contentDataClient = contentDataClient;
        this.aggregator = aggregator;
    }

    @GetMapping
    public Page<MuseumJson> getMuseums(Pageable pageable) {
        Page<MuseumJson> museums = contentDataClient.getMuseums(pageable);
        museums.forEach(aggregator::enrichMuseum);

        return museums;
    }

    @GetMapping(params = {"title"})
    public Page<MuseumJson> filterMuseumsByName(@RequestParam(value = "title") String title) {
        Page<MuseumJson> museums = contentDataClient.filterMuseumsByTitle(title);
        museums.forEach(aggregator::enrichMuseum);

        return museums;
    }

    @GetMapping("/{uuid}")
    public MuseumJson getMuseum(@PathVariable("uuid") UUID uuid) {
        MuseumJson museum = contentDataClient.getMuseum(uuid);
        aggregator.enrichMuseum(museum);

        return museum;
    }

    @PostMapping
    public MuseumJson addMuseum(@RequestBody MuseumJson museumJson) {
        MuseumJson addedMuseum = contentDataClient.addMuseum(museumJson);

        return addedMuseum;
    }

    @PatchMapping
    public MuseumJson editMuseum(@RequestBody MuseumJson museumJson) {
        MuseumJson editedMuseum = contentDataClient.editMuseum(museumJson);

        return editedMuseum;
    }
}
