package org.example.products.service;

import org.example.products.data.Product;
import org.example.products.data.Shop;
import org.example.products.data.TypeProduct;

import java.time.LocalDate;

public interface ShopService {
    void putMarkUpInShop(Shop shop, TypeProduct typeProduct, double percent);
    void putProductInShop(Shop shop , Product product);
    void loweringThePriceIfCloseToExpiration(Shop shop, Integer days, double percent );
}
