package si.fri.prpo.skupina7.config;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExternalApiProperties {
    private String url = "https://v2.jokeapi.dev/joke/Programming?type=single";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}