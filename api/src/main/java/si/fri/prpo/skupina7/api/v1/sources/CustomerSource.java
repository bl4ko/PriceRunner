package si.fri.prpo.skupina7.api.v1.sources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina7.Customer;
import si.fri.prpo.skupina7.beans.CustomerBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerSource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private CustomerBean customerBean;

    @Operation(description = "Returns all customers", summary = "List of all customers")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Customers",
                    content = @Content(schema = @Schema(implementation = Customer.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Total count of customers")}
            )})
    @GET
    public Response getCustomers() {

        // QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        int customersCount = customerBean.getCustomerCount();

        return Response
                .ok(customerBean.getCustomers())
                .header("X-Total-Count", customersCount)
                .build();
    }

    @Operation(description = "Returns customer with given id", summary = "Customer with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Customer",
                    content = @Content(schema = @Schema(implementation = Customer.class))
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Customer not found"
            )})
    @GET
    @Path("{id}")
    public Response getCustomer(@Parameter(description = "Customer ID", required = true) @PathParam("id") Integer id) {
        Customer customer = customerBean.getCustomer(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(customer).build();
    }

    @Operation(description = "Deletes a customer with given id", summary = "Delete customer with given id")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Customer deleted"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Customer not found"
            )})
    @DELETE
    @Path("{id}")
    public Response deleteCustomer(@Parameter(description = "Customer ID", required = true) @PathParam("id") Integer id) {
        Customer customer = customerBean.getCustomer(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        customerBean.deleteCustomer(id);
        return Response.ok().build();
    }
}
