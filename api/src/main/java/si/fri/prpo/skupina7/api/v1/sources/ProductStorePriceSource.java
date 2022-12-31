package si.fri.prpo.skupina7.api.v1.sources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.fri.prpo.skupina7.ProductStorePrice;
import si.fri.prpo.skupina7.beans.ProductStorePriceBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Tag(name = "Product Store Price Object", description = "Product Store Price Object operations")
@Path("product-store-prices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, DELETE, PUT, OPTIONS")
public class ProductStorePriceSource {
    @Context
    protected UriInfo uriInfo;

    @Inject
    private ProductStorePriceBean productStorePriceBean;

    @Operation(description = "Returns all product store price objects", summary = "List of all product store price objects")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Product Store Price Object",
                    content = @Content(schema = @Schema(implementation = ProductStorePrice.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Total count of product store price objects")}
            )})
    @GET
    public Response getProductStorePrices() {
        int productStorePriceCount = productStorePriceBean.getProductStorePriceCount(1);

        return Response
                .ok(productStorePriceBean.getProductStorePrices())
                .header("X-Total-Count", productStorePriceCount)
                .build();
    }

    @Operation(description = "Returns all stores with prices for product", summary = "List of all stores with prices for product")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Product Store Price",
                    content = @Content(schema = @Schema(implementation = ProductStorePrice.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Total count of product store prices")}
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Product not found"
            )})
    @GET
    @Path("/product/{id}")
    public Response getStorePriceTuples(@Parameter(description = "Product ID", required = true) @PathParam("id") Integer productId) {
        int productStorePriceCount = productStorePriceBean.getProductStorePriceCount(productId);

        List<ProductStorePrice> stores = productStorePriceBean.getStorePriceTuples(productId);

        if (stores == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(stores).header("X-Total-Count", productStorePriceCount).build();
    }

    @Operation(description = "Creates a new product store price", summary = "Create a new product store price")
    @APIResponses({
            @APIResponse(
                    responseCode = "201",
                    description = "Product Store Price created"
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid input"
            )})
    @POST
    public Response createProductStorePrice(@Parameter(description = "Product Store Price object", required = true) ProductStorePrice productStorePrice) {
        if (productStorePrice == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        productStorePrice = productStorePriceBean.createProductStorePrice(productStorePrice);

        if (productStorePrice.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(productStorePrice).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(productStorePrice).build();
        }
    }

    //    Delete
    @Operation(description = "Deletes a product store price", summary = "Delete a product store price")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Product Store Price deleted"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Product Store Price not found"
            )})
    @DELETE
    @Path("{id}")
    public Response deleteProductStorePrice(@Parameter(description = "Product Store Price ID", required = true) @PathParam("id") Integer productStorePriceId) {
        boolean deleted = productStorePriceBean.deleteProductStorePrice(productStorePriceId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
