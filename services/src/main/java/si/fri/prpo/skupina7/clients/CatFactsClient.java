package si.fri.prpo.skupina7.clients;

import si.fri.prpo.skupina7.config.ExternalApiProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
public class CatFactsClient {
    private final Logger log = Logger.getLogger(CatFactsClient.class.getName());

    @Inject
    private ExternalApiProperties externalApiProperties;

    private Client client;

    @PostConstruct
    private void init() {
        log.info("Initializing CatFactsClient");
        client = javax.ws.rs.client.ClientBuilder.newClient();
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroying CatFactsClient");
        client.close();
    }

    public String getCatFact() {
        Response response = null;

        try {
            response = client.target(externalApiProperties.getUrl())
                    .request(MediaType.APPLICATION_JSON)
                    .header("content-type", "application/json")
                    .get();
        } catch (Exception e) {
            log.severe(e.getMessage());
        }

        JsonObject jsonObject = response.readEntity(JsonObject.class);
        String catFact = jsonObject.getString("fact");

        return catFact;


    }

}
