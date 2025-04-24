package model;

import java.util.List;

public class Order {
    private int orderId;
    private int customerId;
    private List<CartItem> items;

    public Order() {}

    public Order(int orderId, int customerId, List<CartItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
}
