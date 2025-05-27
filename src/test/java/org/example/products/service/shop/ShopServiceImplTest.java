package org.example.products.service.shop;

import org.example.products.data.Cashier;
import org.example.products.data.Product;
import org.example.products.data.Shop;
import org.example.products.data.TypeProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceImplTest {
    private ShopService shopService;
    private Shop shop;

    @BeforeEach
    void setUp() {
        shopService = new ShopServiceImpl();
        shop = new Shop();
        shop.setPercentMarkupByType(new EnumMap<>(TypeProduct.class));
        shop.setProduct(new HashSet<>());
        shop.setIssuedReceipts(new HashSet<>());
        shop.setSoldProducts(new HashSet<>());
        shop.setCheckout(new HashMap<>());
    }

    @Test
    void testPutProductInShop_valid() {
        Product product = new Product(1, "Milk", 2.0,   TypeProduct.FOOD, LocalDate.of(2026,10,10),10);
        shopService.putProductInShop(shop, product);
        assertTrue(shop.getProduct().contains(product));
    }

    @Test
    void testPutProductInShop_nullShop_throws() {
        Product product = new Product(1, "Milk", 2.0, TypeProduct.FOOD, null,0);
        assertThrows(IllegalArgumentException.class, () -> shopService.putProductInShop(null, product));
    }

    @Test
    void testPutProductInShop_nullProduct_throws() {
        assertThrows(IllegalArgumentException.class, () -> shopService.putProductInShop(shop, null));
    }
    @Test
    void testPutMarkUpInShop() {
        shopService.putMarkUpInShop(shop, TypeProduct.FOOD, 10.0);
        assertEquals(10.0, shop.getPercentMarkupByType().get(TypeProduct.FOOD));
    }

    @Test
    void testCalculateCashierSalaries() {
        Cashier c1 = new Cashier("C1", 1,1000);
        Cashier c2 = new Cashier("C2", 2, 800);
        shop.getCheckout().put(1,c1);
        shop.getCheckout().put(2,c2);
        shopService.calculateCashierSalaries(shop);
        assertEquals(1800.0, shopService.calculateCashierSalaries(shop), 0.001);
    }


}