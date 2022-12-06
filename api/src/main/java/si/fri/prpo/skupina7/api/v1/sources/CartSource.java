package si.fri.prpo.skupina7.api.v1.sources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina7.Cart;
import si.fri.prpo.skupina7.beans.CartBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("carts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartSource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private CartBean cartBean;

    @Operation(description = "Returns all carts", summary = "List of all carts")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Cart",
                    content = @Content(schema = @Schema(implementation = Cart.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Total count of carts")}
            )})
    @GET
    public Response getCarts() {
        int cartCount = cartBean.getCartCount();

        return Response
                .ok(cartBean.getCarts())
                .header("X-Total-Count", cartCount)
                .build();
    }

    @Operation(description = "Returns cart with given id", summary = "Cart with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Cart",
                    content = @Content(schema = @Schema(implementation = Cart.class))
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Cart not found"
            )})
    @GET
    @Path("{id}")
    public Response getCart(@Operation(description = "Cart ID", summary = "Id of the cart") @PathParam("id") Integer id) {
        Cart cart = cartBean.getCart(id);

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(cart).build();
    }

    @Operation(description = "Deletes cart with given id", summary = "Delete cart with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Cart deleted"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Cart not found"
            )})
    @DELETE
    @Path("{id}")
    public Response deleteCart(@Operation(description = "Cart ID", summary = "Id of the cart") @PathParam("id") Integer id) {
        Cart cart = cartBean.getCart(id);

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        cartBean.deleteCart(id);

        return Response.ok().build();
    }


}
