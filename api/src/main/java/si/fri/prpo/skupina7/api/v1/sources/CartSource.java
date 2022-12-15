package si.fri.prpo.skupina7.api.v1.sources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.fri.prpo.skupina7.Cart;
import si.fri.prpo.skupina7.beans.CartBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Tag(name = "Cart", description = "Cart operations")
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
    public Response getCart(@Parameter(description = "Cart ID", required = true) @PathParam("id") Integer id) {
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
    public Response deleteCart(@Parameter(description = "Cart ID", required = true) @PathParam("id") Integer id) {
        Cart cart = cartBean.getCart(id);

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        cartBean.deleteCart(id);

        return Response.ok().build();
    }

    @Operation(description = "Creates new cart", summary = "Create new cart")
    @APIResponses({
            @APIResponse(
                    responseCode = "201",
                    description = "Cart created"
            )})
    @POST
    public Response createCart(Cart cart) {
        cartBean.createCart(cart);

        return Response
                .created(uriInfo.getAbsolutePathBuilder().path(cart.getId().toString()).build())
                .build();
    }

    @Operation(description = "Updates cart with given id", summary = "Update cart with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Cart updated"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Cart not found"
            )})
    @PUT
    @Path("{id}")
    public Response updateCart(@Parameter(description = "Cart ID", required = true) @PathParam("id") Integer id, Cart cart) {
        Cart existingCart = cartBean.getCart(id);

        if (existingCart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        cartBean.updateCart(id, cart);

        return Response.ok().build();
    }

    // Add product to cart
    @Operation(description = "Adds product by id to cart with given id", summary = "Add product by id to cart with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Product added to cart"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Cart or product not found"
            )})
    @PUT
    @Path("{id}/add/{productId}")
    public Response addProductToCart(@Parameter(description = "Cart ID", required = true) @PathParam("id") Integer id, @Parameter(description = "Product ID", required = true) @PathParam("productId") Integer productId) {

        try {
            cartBean.addProductToCart(id, productId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Remove product from cart
    @Operation(description = "Removes product by id from cart with given id", summary = "Remove product by id from cart with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Product removed from cart"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Cart or product not found"
            )})
    @PUT
    @Path("{id}/remove/{productId}")
    public Response removeProductFromCart(@Parameter(description = "Cart ID", required = true) @PathParam("id") Integer id, @Parameter(description = "Product ID", required = true) @PathParam("productId") Integer productId) {

        if (cartBean.removeProductFromCart(id, productId)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
