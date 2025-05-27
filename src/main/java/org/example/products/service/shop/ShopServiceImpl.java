package org.example.products.service.shop;

import org.example.products.data.*;
import org.example.products.service.shop.ShopService;

public class ShopServiceImpl implements ShopService {

    @Override
    public void putMarkUpInShop(Shop shop, TypeProduct typeProduct, double percent) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }
        if (typeProduct == null) {
            throw new IllegalArgumentException("TypeProduct cannot be null");
        }
        if (percent < 0) {
            throw new IllegalArgumentException("Markup cannot be negative");
        }
        shop.getPercentMarkupByType().put(typeProduct, percent);
    }

    @Override
    public void putProductInShop(Shop shop, Product product) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        shop.getProduct().add(product);
    }

    @Override
    public void loweringThePriceIfCloseToExpiration(Shop shop, Integer days, double percentage) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }
        if (days == null || days < 0) {
            throw new IllegalArgumentException("Days cannot be null or negative");
        }
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage is wrong");
        }
        shop.setLowerPriceIfDaysUntilExpIsUnder(days);
        shop.setPercentageIfCloserUntilExp(percentage);
    }

    @Override
    public void addReceiptToShop(Shop shop, Receipt receipt) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }
        if (receipt == null) {
            throw new IllegalArgumentException("Receipt cannot be null");
        }
        shop.getIssuedReceipts().add(receipt);
        shop.getSoldProducts().addAll(receipt.getItems().keySet());
    }

    @Override
    public double calculateRevenue(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }
        return shop.getIssuedReceipts().stream().mapToDouble(Receipt::getTotal).sum();
    }

    @Override
    public double calculateDeliveryCosts(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }
        return shop.getProduct().stream().mapToDouble(Product::getDeliveryPrice).sum();
    }

    @Override
    public double calculateCashierSalaries(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }
        return shop.getCheckout().values().stream().mapToDouble(Cashier::getSalary).sum();
    }

    @Override
    public double calculateProfit(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }
        return calculateRevenue(shop) - calculateCashierSalaries(shop) - calculateDeliveryCosts(shop);
    }


}
