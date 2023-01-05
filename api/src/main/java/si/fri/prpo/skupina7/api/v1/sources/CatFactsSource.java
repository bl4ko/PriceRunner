package si.fri.prpo.skupina7.api.v1.sources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.fri.prpo.skupina7.beans.CatFactsBean;

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
@Tag(name = "Cat Facts", description = "Facts of Cats")
@Path("cat-facts")
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, DELETE, PUT, OPTIONS")
public class CatFactsSource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private CatFactsBean catFactsBean;

    @Operation(description = "Returns a random cat fact", summary = "Random cat fact")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Cat Fact"
            )
            , @APIResponse(
            responseCode = "404",
            description = "Cat Fact not found"
    )})
    @GET
    public Response getCatFact() {

        String catFact = catFactsBean.getCatFact();

//        Convert to JSON
        String jsonObj = "{\"fact\": \"" + catFact + "\"}";
        return Response.ok(jsonObj).build();
    }
}
