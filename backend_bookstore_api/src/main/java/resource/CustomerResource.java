package resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Customer;
import service.CustomerService;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerService service = CustomerService.getInstance();

    @GET
    public List<Customer> getAll() {
        return service.getAllCustomers();
    }

    @GET
    @Path("/{id}")
    public Customer getById(@PathParam("id") int id) {
        return service.getCustomer(id);
    }

    @POST
    public Response add(Customer customer, @Context UriInfo uriInfo) {
        Customer created = service.addCustomer(customer);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.getId()));
        return Response.created(builder.build()).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Customer update(@PathParam("id") int id, Customer customer) {
        return service.updateCustomer(id, customer);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        service.deleteCustomer(id);
        return Response.noContent().build();
    }
}
