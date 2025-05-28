package org.example.products.service.shop;

import org.example.products.data.*;
import org.example.products.service.cashier.CashierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

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
    void testPutProductInShopValid() {
        Product product = new Product(1, "Milk", 2,   TypeProduct.FOOD, LocalDate.of(2026,10,10),10);
        shopService.putProductInShop(shop, product);
        assertTrue(shop.getProduct().contains(product));
    }

    @Test
    void testPutProductInShopNullShopThrows() {
        Product product = new Product(1, "Milk", 2, TypeProduct.FOOD, null,0);
        assertThrows(IllegalArgumentException.class, () -> shopService.putProductInShop(null, product));
    }

    @Test
    void testPutProductInShopNullProductThrows() {
        assertThrows(IllegalArgumentException.class, () -> shopService.putProductInShop(shop, null));
    }
    @Test
    void testPutMarkUpInShop() {
        shopService.putMarkUpInShop(shop, TypeProduct.FOOD, 10);
        assertEquals(10, shop.getPercentMarkupByType().get(TypeProduct.FOOD));
    }

    @Test
    void testCalculateCashierSalaries() {
        Cashier c1 = new Cashier("C1", 1,1000);
        Cashier c2 = new Cashier("C2", 2, 800);
        shop.getCheckout().put(1,c1);
        shop.getCheckout().put(2,c2);
        shopService.calculateCashierSalaries(shop);
        assertEquals(1800, shopService.calculateCashierSalaries(shop));
    }
    @Test
    void testPutMarkUpInShopNullShopThrows() {
        assertThrows(IllegalArgumentException.class, () -> shopService.putMarkUpInShop(null, TypeProduct.FOOD, 10));
    }

    @Test
    void testPutMarkUpInShopNullTypeProductThrows() {
        assertThrows(IllegalArgumentException.class, () -> shopService.putMarkUpInShop(shop, null, 10));
    }

    @Test
    void testPutMarkUpInShopNegativePercentThrows() {
        assertThrows(IllegalArgumentException.class, () -> shopService.putMarkUpInShop(shop, TypeProduct.FOOD, -5));
    }

    @Test
    void testLoweringThePriceIfCloseToExpirationValid() {
        shopService.loweringThePriceIfCloseToExpiration(shop, 5, 20);
        assertEquals(5, shop.getLowerPriceIfDaysUntilExpIsUnder());
        assertEquals(20.0, shop.getPercentageIfCloserUntilExp());
    }

    @Test
    void testLoweringThePriceIfCloseToExpirationNullShopThrows() {
        assertThrows(IllegalArgumentException.class, () -> shopService.loweringThePriceIfCloseToExpiration(null, 5, 20));
    }

    @Test
    void testLoweringThePriceIfCloseToExpirationNegativeDaysThrows() {
        assertThrows(IllegalArgumentException.class, () -> shopService.loweringThePriceIfCloseToExpiration(shop, -1, 20));
    }

    @Test
    void testLoweringThePriceIfCloseToExpirationInvalidPercentageThrows() {
        assertThrows(IllegalArgumentException.class, () -> shopService.loweringThePriceIfCloseToExpiration(shop, 5, -10));
        assertThrows(IllegalArgumentException.class, () -> shopService.loweringThePriceIfCloseToExpiration(shop, 5, 110));
    }

    @Test
    void testAddReceiptToShopValid() {
        Cashier cashier = new Cashier("John", 1, 1000);
        Product product = new Product(1, "Milk", 2.0, TypeProduct.FOOD, LocalDate.of(2026, 10, 10), 10);
        Receipt receipt = new Receipt(cashier, Map.of(product, 2), 4.0);

        shopService.addReceiptToShop(shop, receipt);

        assertTrue(shop.getIssuedReceipts().contains(receipt));
        assertTrue(shop.getSoldProducts().contains(product));
    }

    @Test
    void testAddReceiptToShopNullShopThrows() {
        Cashier cashier = new Cashier("John", 1, 1000);
        Product product = new Product(1, "Milk", 2.0, TypeProduct.FOOD, LocalDate.of(2026, 10, 10), 10);
        Receipt receipt = new Receipt(cashier, Map.of(product, 2), 4);

        assertThrows(IllegalArgumentException.class, () -> shopService.addReceiptToShop(null, receipt));
    }

    @Test
    void testAddReceiptToShopNullReceiptThrows() {
        assertThrows(IllegalArgumentException.class, () -> shopService.addReceiptToShop(shop, null));
    }

    @Test
    void testCalculateRevenueValid() {
        Cashier cashier = new Cashier("John", 1, 1000);
        Receipt receipt1 = new Receipt(cashier, Map.of(), 100);
        Receipt receipt2 = new Receipt(cashier, Map.of(), 200);

        shop.getIssuedReceipts().add(receipt1);
        shop.getIssuedReceipts().add(receipt2);

        assertEquals(300, shopService.calculateRevenue(shop));
    }

    @Test
    void testCalculateRevenueEmptyReceipts() {
        assertEquals(0, shopService.calculateRevenue(shop));
    }


}