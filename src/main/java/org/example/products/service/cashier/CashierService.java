package org.example.products.service.cashier;

import org.example.products.data.Cashier;
import org.example.products.data.Client;
import org.example.products.data.Product;
import org.example.products.data.Shop;

import java.io.IOException;
import java.time.LocalDate;

public interface CashierService {
    double calculateSellingPrice(Shop shop, Product product, LocalDate today);
     void processPurchase(Client client, Shop shop, Cashier cashier, LocalDate today);
}
