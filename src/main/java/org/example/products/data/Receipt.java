package org.example.products.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Receipt implements Serializable {
    private static int counter = 1;
    private final int id;
    private final Cashier cashier;
    private final LocalDateTime dateTime;
    private final Map<Product, Integer> items;

    public Receipt(Cashier cashier, Map<Product, Integer> items, double total) {

        this.id = counter++;
        this.cashier = cashier;
        this.dateTime = LocalDateTime.now();
        this.items = new HashMap<>(items);
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    private final double total;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Receipt.counter = counter;
    }




}

