package si.fri.prpo.skupina7.config;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExternalApiProperties {
    private final String url = "https://v2.jokeapi.dev/joke/Programming?type=single";

    public String getUrl() {
        return url;
    }

}