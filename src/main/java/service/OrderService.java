package service;

import model.CartItem;
import model.Order;
import exception.CartNotFoundException;

import java.util.*;

public class OrderService {
    private static final Map<Integer, List<Order>> orderHistory = new HashMap<>();
    private static int orderIdCounter = 1;

    private static final OrderService instance = new OrderService();
    public static OrderService getInstance() {
        return instance;
    }

    private OrderService() {}

    public Order placeOrder(int customerId, List<CartItem> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) {
            throw new CartNotFoundException(customerId);
        }

        Order order = new Order(orderIdCounter++, customerId, new ArrayList<>(cartItems));
        orderHistory.computeIfAbsent(customerId, k -> new ArrayList<>()).add(order);
        CartService.getInstance().getCart(customerId).clear(); // Clear cart after placing order
        return order;
    }

    public List<Order> getOrders(int customerId) {
        return orderHistory.getOrDefault(customerId, new ArrayList<>());
    }

    public Order getOrderById(int customerId, int orderId) {
        return getOrders(customerId).stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }
}
