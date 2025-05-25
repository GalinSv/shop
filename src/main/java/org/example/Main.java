package org.example;

import org.example.products.data.*;
import org.example.products.service.CashierService;
import org.example.products.service.CashierServiceImpl;
import org.example.products.service.ShopService;
import org.example.products.service.ShopServiceImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Shop shop1 = new Shop("pep");
        Product product = new Product(1, "Coconut", 20, TypeProduct.FOOD, LocalDate.of(2026, 2, 2), 1);
        Product product2 = new Product(2, "banana", 20, TypeProduct.NONFOOD, LocalDate.of(2026, 2, 2), 10);
        Product product3 = new Product(3, "lemon", 20, TypeProduct.FOOD, LocalDate.of(2025, 5, 27), 5);
        ShopService shopService = new ShopServiceImpl();
        shopService.putMarkUpInShop(shop1, TypeProduct.FOOD, 5);
        shopService.putMarkUpInShop(shop1, TypeProduct.NONFOOD, 20);
        shopService.putProductInShop(shop1, product);
        shopService.putProductInShop(shop1, product2);
        shopService.putProductInShop(shop1, product3);
        shopService.loweringThePriceIfCloseToExpiration(shop1, 1, 10);

        CashierService cashierService = new CashierServiceImpl();

        Cashier cashier = new Cashier( "John", 1,1500);
        shop1.getCheckout().put(1, cashier);
        Client client = new Client( 20.0);
        client.addToCart(product, 1);
        client.addToCart(product2, 2);
        client.addToCart(product3, 2);
        try {
            cashierService.processPurchase(client, shop1, 1, LocalDate.now());
        } catch (Exception e) {
            System.out.println("Purchase error: " + e.getMessage());
        }

        System.out.println("After purchase, shop inventory:");
        shop1.getProduct().forEach(p -> System.out.println(p.getName() + " - left: " + p.getQuantity()));
    }
}