package guru.qa.rococo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RococoGatewayServiceConfig {

    public static final int THREE_MB = 3145728;

    private final String rococoUserdataBaseUri;

    @Autowired
    public RococoGatewayServiceConfig(@Value("${rococo-userdata.base-uri}") String rococoUserdataBaseUri) {
        this.rococoUserdataBaseUri = rococoUserdataBaseUri;
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder().codecs(
                        configurer -> configurer.defaultCodecs().maxInMemorySize(THREE_MB)).build())
                .build();
    }

    //soap
//    @Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setContextPath("guru.qa.rococo.userdata.wsdl");
//        return marshaller;
//    }
//
//    @Bean
//    public SoapUserDataClient userDataClient(Jaxb2Marshaller marshaller) {
//        SoapUserDataClient client = new SoapUserDataClient();
//        client.setDefaultUri(nifflerUserdataBaseUri + "/ws");
//        client.setMarshaller(marshaller);
//        client.setUnmarshaller(marshaller);
//        return client;
//    }
}
