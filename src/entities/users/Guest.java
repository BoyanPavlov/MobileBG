package entities.users;

import entities.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Guest extends User {
    private List<Product> cart;

    public Guest() {
        super("Guest", null, null); // No password or email
        this.cart = new ArrayList<>(); // Initialize an empty cart
    }

    public List<Product> getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        this.cart.add(product);
    }

    public void clearCart() {
        this.cart.clear();
    }

    @Override
    public Set<Role> getRoles() {
        return Set.of();
    }

    @Override
    public String toString() {
        return "Guest{name='" + getName() + "', role='" + getRoles() + "', cart=" + cart + "}";
    }
}
