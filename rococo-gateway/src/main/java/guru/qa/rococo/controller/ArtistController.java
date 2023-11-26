package guru.qa.rococo.controller;


import guru.qa.rococo.service.api.UserDataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {


    private final UserDataClient userDataClient;


    @Autowired
    public ArtistController(UserDataClient userDataClient) {

        this.userDataClient = userDataClient;

    }


}
