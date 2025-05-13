package resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.CartItem;
import service.CartService;

import java.util.List;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    private final CartService cartService = CartService.getInstance();

    @GET
    public List<CartItem> getCart(@PathParam("customerId") int customerId) {
        return cartService.getCart(customerId);
    }

    @POST
    @Path("/items")
    public Response addItem(@PathParam("customerId") int customerId, CartItem item) {
        cartService.addItem(customerId, item);
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    @PUT
    @Path("/items/{bookId}")
    public CartItem updateItem(
            @PathParam("customerId") int customerId,
            @PathParam("bookId") int bookId,
            CartItem updatedItem
    ) {
        cartService.updateItem(customerId, bookId, updatedItem.getQuantity());
        return updatedItem;
    }

    @DELETE
    @Path("/items/{bookId}")
    public Response deleteItem(
            @PathParam("customerId") int customerId,
            @PathParam("bookId") int bookId
    ) {
        cartService.removeItem(customerId, bookId);
        return Response.noContent().build();
    }
}
