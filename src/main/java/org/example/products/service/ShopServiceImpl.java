package org.example.products.service;

import org.example.products.data.Product;
import org.example.products.data.Shop;
import org.example.products.data.TypeProduct;

import java.time.LocalDate;

public class ShopServiceImpl implements ShopService{

    @Override
    public void putMarkUpInShop(Shop shop, TypeProduct typeProduct, double percent) {
        shop.getPercentMarkupByType().put(typeProduct,percent);
    }

    @Override
    public void putProductInShop(Shop shop, Product product) {
        shop.getProduct().add(product);
    }

    @Override
    public void loweringThePriceIfCloseToExpiration(Shop shop, Integer days, double percecentage) {
        shop.setLowerPriceIfDaysUntilExpIsUnder(days);
        shop.setPercentageIfCloserUntilExp(percecentage);
    }


}
