package si.fri.prpo.skupina7.api.v1.sources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.fri.prpo.skupina7.beans.JokeBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Tag(name = "Jokes", description = "Random Jokes")
@Path("jokes")
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET")
public class JokeSource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private JokeBean jokeBean;

    @Operation(description = "Returns a random joke", summary = "Random joke")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Joke"
            )
            , @APIResponse(
            responseCode = "404",
            description = "Joke not found"
    )})
    @GET
    public Response getJoke() {

        String joke = jokeBean.getJoke();

        joke = joke.replace("\n", "\\n");
        joke = joke.replaceAll("\"", "'");

        String jsonObject = "{\"joke\": \"" + joke + "\"}";
        return Response.ok(jsonObject).build();
    }
}
