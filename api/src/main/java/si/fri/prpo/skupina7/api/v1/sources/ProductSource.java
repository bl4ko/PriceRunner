package si.fri.prpo.skupina7.api.v1.sources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina7.Product;
import si.fri.prpo.skupina7.beans.ProductBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductSource {
    @Context
    protected UriInfo uriInfo;

    @Inject
    private ProductBean productBean;

    @Operation(description = "Returns all products", summary = "List of all products")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Product",
                    content = @Content(schema = @Schema(implementation = Product.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Total count of products")}
            )})
    @GET
    public Response getProducts() {
        int productCount = productBean.getProductCount();

        return Response
                .ok(productBean.getProducts())
                .header("X-Total-Count", productCount)
                .build();
    }

    @Operation(description = "Returns product with given id", summary = "Product with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Product",
                    content = @Content(schema = @Schema(implementation = Product.class))
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @GET
    @Path("{id}")
    public Response getProduct(@Parameter(description = "Product ID", required = true) @PathParam("id") Integer id) {
        Product product = productBean.getProduct(id);

        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(product).build();
    }

    @Operation(description = "Delete a Product with given id", summary = "Delete Product with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Product deleted"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @DELETE
    @Path("{id}")
    public Response deleteProduct(@Parameter(description = "Product ID", required = true) @PathParam("id") Integer id) {
        Product product = productBean.getProduct(id);

        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        productBean.deleteProduct(id);

        return Response.noContent().build();
    }
}
