package si.fri.prpo.skupina7.api.v1.sources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina7.Store;
import si.fri.prpo.skupina7.beans.StoreBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("store")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoreSource {
    @Context
    protected UriInfo uriInfo;

    @Inject
    private StoreBean storeBean;

    @Operation(description = "Returns all stores", summary = "List of all stores")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Stores",
                    content = @Content(schema = @Schema(implementation = Store.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Total count of stores")}
            )})
    @GET
    public Response getStores() {
        int storesCount = storeBean.getStoreCount();

        return Response
                .ok(storeBean.getStores())
                .header("X-Total-Count", storesCount)
                .build();
    }

    @Operation(description = "Returns store with given id", summary = "Store with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Store",
                    content = @Content(schema = @Schema(implementation = Store.class))
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Store not found"
            )
    })
    @GET
    @Path("{id}")
    public Response getStore(@Parameter(description = "Store ID", required = true) @PathParam("id") Integer id) {
        Store store = storeBean.getStore(id);

        if (store == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(store).build();
    }

    @Operation(description = "Delete a store with given id", summary = "Delete store with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Store deleted"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Store not found"
            )
    })
    @DELETE
    @Path("{id}")
    public Response deleteStore(@Parameter(description = "Store ID", required = true) @PathParam("id") Integer id) {
        Store store = storeBean.getStore(id);

        if (store == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        storeBean.deleteStore(id);

        return Response.noContent().build();
    }
}
