package service;

import model.CartItem;
import exception.CartNotFoundException;

import java.util.*;

public class CartService {
    private static final Map<Integer, List<CartItem>> customerCarts = new HashMap<>();
    private static final CartService instance = new CartService();

    public static CartService getInstance() {
        return instance;
    }

    private CartService() {}

    public List<CartItem> getCart(int customerId) {
        return customerCarts.getOrDefault(customerId, new ArrayList<>());
    }

    public void addItem(int customerId, CartItem item) {
        List<CartItem> cart = customerCarts.computeIfAbsent(customerId, k -> new ArrayList<>());

        for (CartItem existing : cart) {
            if (existing.getBookId() == item.getBookId()) {
                existing.setQuantity(existing.getQuantity() + item.getQuantity());
                return;
            }
        }
        cart.add(item);
    }

    public void updateItem(int customerId, int bookId, int quantity) {
        List<CartItem> cart = customerCarts.get(customerId);
        if (cart == null) throw new CartNotFoundException(customerId);

        for (CartItem item : cart) {
            if (item.getBookId() == bookId) {
                item.setQuantity(quantity);
                return;
            }
        }
        throw new RuntimeException("Book not found in cart.");
    }

    public void removeItem(int customerId, int bookId) {
        List<CartItem> cart = customerCarts.get(customerId);
        if (cart == null) throw new CartNotFoundException(customerId);

        cart.removeIf(item -> item.getBookId() == bookId);
    }
}
