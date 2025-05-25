package org.example.products.service;

import org.example.products.data.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;

public class CashierServiceImpl implements CashierService {

    @Override
    public double calculateSellingPrice(Shop shop, Product product, LocalDate today) {

        if (product.getExpirationDate().isBefore(today)) {
            throw new IllegalArgumentException("Product " + product.getName() + " is expired and cannot be sold.");
        }

        double deliveryPrice = product.getDeliveryPrice();
        double markupPercent = shop.getPercentMarkupByType().getOrDefault(product.getTypeProduct(), 0.0);
        double priceWithMarkup = deliveryPrice + (deliveryPrice * markupPercent / 100);

        long daysUntilExpiration = ChronoUnit.DAYS.between(today, product.getExpirationDate());
        if (daysUntilExpiration < shop.getLowerPriceIfDaysUntilExpIsUnder()) {
            double discount = shop.getPercentageIfCloserUntilExp();
            priceWithMarkup *= (1 - discount / 100);
        }


        return priceWithMarkup;
    }

    @Override
    public void processPurchase(Client client, Shop shop, int cashierId, LocalDate today) {
        Map<Product, Integer> cart = client.getCart();
        double total = 0;

        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            int wantedQty = entry.getValue();

            Optional<Product> match = shop.getProduct().stream()
                    .filter(p -> p.getId() == product.getId())
                    .findFirst();

            if (match.isEmpty()) {
                throw new RuntimeException("Product not found in shop: " + product.getName());
            }

            Product shopProduct = match.get();

            if (shopProduct.getQuantity() < wantedQty) {
                throw new InsufficientQuantityException("Not enough " + shopProduct.getName() +
                        ": wanted " + wantedQty + ", available " + shopProduct.getQuantity());
            }
            CashierService cashierService = new CashierServiceImpl();
            double price = cashierService.calculateSellingPrice(shop, shopProduct, today);
            shopProduct.setSellPrice(price);
            total += price * wantedQty;
        }

        if (client.getMoney() < total) {
            throw new RuntimeException("Client doesn't have enough money. Needed: " + total);
        }

        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product shopProduct = shop.getProduct().stream()
                    .filter(p -> p.getId() == entry.getKey().getId())
                    .findFirst()
                    .get();

            shopProduct.setQuantity(shopProduct.getQuantity() - entry.getValue());
        }

        client.deductMoney(total);

        Receipt receipt = new Receipt(shop.getCheckout().get(cashierId), cart, total);
        try {
            receipt.printAndSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
