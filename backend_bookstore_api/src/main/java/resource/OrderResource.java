package resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.CartItem;
import model.Order;
import service.CartService;
import service.OrderService;

import java.util.List;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final OrderService orderService = OrderService.getInstance();
    private final CartService cartService = CartService.getInstance();

    @POST
    public Response placeOrder(@PathParam("customerId") int customerId, @Context UriInfo uriInfo) {
        List<CartItem> cart = cartService.getCart(customerId);
        Order order = orderService.placeOrder(customerId, cart);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(order.getOrderId()));
        return Response.created(builder.build()).entity(order).build();
    }

    @GET
    public List<Order> getOrders(@PathParam("customerId") int customerId) {
        return orderService.getOrders(customerId);
    }

    @GET
    @Path("/{orderId}")
    public Order getOrder(@PathParam("customerId") int customerId, @PathParam("orderId") int orderId) {
        Order order = orderService.getOrderById(customerId, orderId);
        if (order == null) throw new NotFoundException("Order not found.");
        return order;
    }
}
