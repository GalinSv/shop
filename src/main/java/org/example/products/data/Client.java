package org.example.products.data;

import java.util.HashMap;
import java.util.Map;

public class Client {
    private double money;
    private Map<Product, Integer> cart;

    public void setMoney(double money) {
        this.money = money;
    }

    public Client(double money) {
        this.money = money;
        this.cart = new HashMap<>();
    }

    public void addToCart(Product product, int quantity) {
        cart.put(product, quantity);
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public double getMoney() {
        return money;
    }

    public void deductMoney(double amount) {
        this.money -= amount;
    }
}
