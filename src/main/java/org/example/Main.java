package org.example;

import org.example.products.data.*;
import org.example.products.service.cashier.CashierService;
import org.example.products.service.cashier.CashierServiceImpl;
import org.example.products.service.shop.ShopService;
import org.example.products.service.shop.ShopServiceImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Shop shop = new Shop();


        ShopService shopService = new ShopServiceImpl();


        Cashier cashier = new Cashier("Ana", 1, 2500);
        Cashier cashier2 = new Cashier("Maria", 2, 2000);
        Cashier cashier3 = new Cashier("Ivan", 3, 1000);
        Cashier cashier4 = new Cashier("Petar", 4, 500);
        shop.getCheckout().put(1, cashier);
        shop.getCheckout().put(2, cashier2);
        shop.getCheckout().put(3, cashier3);
        shop.getCheckout().put(4, cashier4);


        try {
            shopService.putMarkUpInShop(shop, TypeProduct.FOOD, 20.0);
            shopService.putMarkUpInShop(shop, TypeProduct.NONFOOD, 10.0);
        } catch (IllegalArgumentException e) {
            System.err.println("Error setting markup: " + e.getMessage());
        }


        try {
            shopService.loweringThePriceIfCloseToExpiration(shop, 5, 30.0);
        } catch (IllegalArgumentException e) {
            System.err.println("Error setting lowering price policy: " + e.getMessage());
        }


        Product milk = new Product(111, "Milk", 10, TypeProduct.FOOD, LocalDate.of(2026, 10,2), 100);
        Product soap = new Product(222, "Soap", 25, TypeProduct.NONFOOD, LocalDate.of(2026, 10,2), 50);
        Product bread = new Product(333, "Bread", 10, TypeProduct.FOOD, LocalDate.of(2025, 5,29), 20);
        Product apple = new Product(444, "Apple", 25, TypeProduct.NONFOOD, LocalDate.of(2025, 5,29), 50);

        try {
            shopService.putProductInShop(shop, milk);
            shopService.putProductInShop(shop, soap);
            shopService.putProductInShop(shop, bread);
            shopService.putProductInShop(shop, apple);
        } catch (IllegalArgumentException e) {
            System.err.println("Error adding product to shop: " + e.getMessage());
        }


        Client client = new Client(9000);
        client.addToCart(milk, 20);
        client.addToCart(soap, 1);
        client.addToCart(apple, 15);

        Client client2 = new Client(9000);
        client2.addToCart(bread, 20);
        client2.addToCart(soap, 20);
        client2.addToCart(apple, 15);

        CashierService cashierService = new CashierServiceImpl();
        try {
            cashierService.processPurchase(client, shop, cashier2, LocalDate.now());
        }catch(IllegalArgumentException e){
            System.err.println("Error: " + e.getMessage());
        }



        System.out.printf("Client remaining money: %.2f%n", client.getMoney());

        try {
        cashierService.processPurchase(client2, shop, cashier3, LocalDate.now());
        }catch(IllegalArgumentException e){
            System.err.println("Error: " + e.getMessage());
        }

        System.out.printf("Client remaining money: %.2f%n", client2.getMoney());
        for (Product p : shop.getProduct()) {
            System.out.printf("Product: %s, remaining quantity: %d%n", p.getName(), p.getQuantity());
        }

        System.out.printf("Shop Revenue: %.2f%n", shopService.calculateRevenue(shop));
        System.out.printf("Shop Profit: %.2f%n", shopService.calculateProfit(shop));

    }
}