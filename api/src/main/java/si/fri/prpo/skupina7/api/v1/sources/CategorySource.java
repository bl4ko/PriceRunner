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
import si.fri.prpo.skupina7.Category;
import si.fri.prpo.skupina7.beans.CategoryBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Tag(name = "Category", description = "Category operations")
@Path("categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, DELETE, PUT, OPTIONS")
public class CategorySource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private CategoryBean categoryBean;

    @Operation(description = "Returns all categories", summary = "List of all categories")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Categories",
                    content = @Content(schema = @Schema(implementation = Category.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Total count of categories")}
            )})
    @GET
    public Response getCategories() {

        // QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        int categoriesCount = categoryBean.getCategoryCount();

        return Response
                .ok(categoryBean.getCategories())
                .header("X-Total-Count", categoriesCount)
                .build();
    }

    @Operation(description = "Returns category with given id", summary = "Category with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Category",
                    content = @Content(schema = @Schema(implementation = Category.class))
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Category not found"
            )})
    @GET
    @Path("{id}")
    public Response getCategory(@Parameter(description = "Category ID", required = true) @PathParam("id") Integer id) {
        Category category = categoryBean.getCategory(id);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(category).build();
    }

    @Operation(description = "Delete a category with given id", summary = "Delete a category")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Category deleted"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Category not found"
            )})
    @DELETE
    @Path("{id}")
    public Response deleteCategory(@Parameter(description = "Category ID", required = true) @PathParam("id") Integer id) {
        Category category = categoryBean.getCategory(id);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        categoryBean.deleteCategory(id);
        return Response.ok().build();
    }

    @Operation(description = "Create a new category", summary = "Create a new category")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Category created"
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid input"
            )})
    @POST
    public Response createCategory(@Parameter(description = "Category to be created", required = true) Category category) {
        if (category == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        categoryBean.createCategory(category);
        return Response.ok().build();
    }

    @Operation(description = "Update a category with given id", summary = "Update a category")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Category updated"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Category not found"
            )})
    @PUT
    @Path("{id}")
    public Response updateCategory(@Parameter(description = "Category ID", required = true) @PathParam("id") Integer id, @Parameter(description = "Category to be updated", required = true) Category category) {
        Category c = categoryBean.getCategory(id);
        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        categoryBean.updateCategory(id, category);
        return Response.ok().build();
    }
}
