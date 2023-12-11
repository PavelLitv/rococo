package guru.qa.rococo.service.api;

import guru.qa.rococo.model.UserJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserDataClient {

    private final RestTemplate restTemplate;
    private final String rococoUserdataBaseUri;

    @Autowired
    public UserDataClient(RestTemplate restTemplate,
                          @Value("${rococo-userdata.base-uri}") String rococoUserdataBaseUri) {
        this.restTemplate = restTemplate;
        this.rococoUserdataBaseUri = rococoUserdataBaseUri;
    }

    public void createUser(UserJson userJson) {
        String uri = rococoUserdataBaseUri + "/user";
        restTemplate.postForObject(uri, userJson, UserJson.class);
    }
}
