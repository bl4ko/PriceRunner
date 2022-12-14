package si.fri.prpo.skupina7.api.v1.sources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.fri.prpo.skupina7.Category;
import si.fri.prpo.skupina7.Product;
import si.fri.prpo.skupina7.Store;
import si.fri.prpo.skupina7.beans.CategoryBean;
import si.fri.prpo.skupina7.beans.ProductBean;
import si.fri.prpo.skupina7.beans.StoreBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Tag(name = "Product", description = "Product operations")
@Path("products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, DELETE, PUT, OPTIONS")
public class ProductSource {
    @Context
    protected UriInfo uriInfo;

    @Inject
    private ProductBean productBean;

    @Inject
    private CategoryBean categoryBean;

    @Inject
    private StoreBean storeBean;


    //    Example pagination links
    //  http://localhost:8080/v1/products?limit=1&offset=1
    // http://localhost:8080/v1/products?order=id DESC
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

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();

        List<Product> products = productBean.getProducts(query);

        if (products == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response
                .ok(products)
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

    @Operation(description = "Create a new Product", summary = "Create a new Product")
    @APIResponses({
            @APIResponse(
                    responseCode = "201",
                    description = "Product created",
                    content = @Content(schema = @Schema(implementation = Product.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid input"
            )
    })
    @POST
    public Response createProduct(@Parameter(description = "Product to be created", required = true) Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        product = productBean.createProduct(product);

        if (product.getId() != null) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(product)
                    .build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(product).build();
        }
    }

    @Operation(description = "Update a Product with given id", summary = "Update Product with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Product updated",
                    content = @Content(schema = @Schema(implementation = Product.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid input"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @PUT
    @Path("{id}")
    public Response updateProduct(@Parameter(description = "Product ID", required = true) @PathParam("id") Integer id, @Parameter(description = "Product to be updated", required = true) Product product) {
        Product p = productBean.getProduct(id);

        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (product.getName() == null || product.getName().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        product = productBean.updateProduct(id, product);

        return Response.ok(product).build();
    }

    // Get all products with a given category id
    @Operation(description = "Returns all products with a given category id", summary = "List of all products with a given category id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "List of products with a given category id"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Category not found"
            )
    })
    @GET
    @Path("category/{id}")
    public Response getProductsByCategoryId(@Parameter(description = "Category ID", required = true) @PathParam("id") Integer id) {
        // Check if category exists
        Category category = categoryBean.getCategory(id);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(productBean.getProductsByCategoryId(id)).build();
    }

    // Get all products with a given store id
    @Operation(description = "Returns all products with a given store id", summary = "List of all products with a given store id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "List of products with a given store id"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Store not found"
            )
    })
    @GET
    @Path("store/{id}")
    public Response getProductsByStoreId(@Parameter(description = "Store ID", required = true) @PathParam("id") Integer id) {
        // Check if store exists
        Store store = storeBean.getStore(id);
        if (store == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(productBean.getProductsByStoreId(id)).build();
    }
}
