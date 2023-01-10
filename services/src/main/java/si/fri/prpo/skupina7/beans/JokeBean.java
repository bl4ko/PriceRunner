package si.fri.prpo.skupina7.beans;

import si.fri.prpo.skupina7.annotations.NoteCalls;
import si.fri.prpo.skupina7.clients.JokeClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;

@ApplicationScoped
public class JokeBean {

    @Inject
    JokeClient jokeClient;

    @NoteCalls
    public String getJoke() {
        JsonObject joke = jokeClient.getJoke();

        if (joke.containsKey("joke")) {
            String jokeString = joke.getString("joke");
            return jokeString;
        } else {
            return "No joke found";
        }
    }
}
