package org.example.products.data;

import org.example.products.service.CashierService;
import org.example.products.service.CashierServiceImpl;

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
    private final double total;

    public Receipt(Cashier cashier, Map<Product, Integer> items, double total) {

        this.id = counter++;
        this.cashier = cashier;
        this.dateTime = LocalDateTime.now();
        this.items = new HashMap<>(items);
        this.total = total;
    }

    public void printAndSave() throws IOException {

        String filename = "receipt_" + id + ".txt";

        try (PrintWriter writer = new PrintWriter(filename)) {

            writer.println("Receipt ID: "+ id);
            writer.println("Cashier: " +cashier.getName());
            writer.println("Date: "+ dateTime);
            writer.println("Items:");
            for (var entry : items.entrySet()) {
                writer.printf("- %s x%d = %.2f%n", entry.getKey().getName(),

                        entry.getValue(),
                        entry.getKey().getSellPrice() * entry.getValue());
            }

            writer.printf("Total: %.2f%n", total);
        }
    }
}

