package si.fri.prpo.skupina7.api.v1.sources;

import si.fri.prpo.skupina7.beans.CustomerBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerSource {

    @Inject
    private CustomerBean customerBean;

    /*@GET
    public Response getCustomers() {

    }*/
}
