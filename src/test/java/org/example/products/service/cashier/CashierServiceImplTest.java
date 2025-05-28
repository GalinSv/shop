package org.example.products.service.cashier;

import org.example.products.data.*;
import org.example.products.service.cashier.CashierService;
import org.example.products.service.cashier.CashierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CashierServiceImplTest {

    private Shop shop;
    private Product product;
    private Client client;
    private Cashier cashier;
    private CashierService cashierService;

    @BeforeEach
    void setUp() {
        shop = new Shop();
        product = new Product(1, "Milk", 10, TypeProduct.FOOD, LocalDate.of(2026, 1, 1), 2);
        cashier = new Cashier("Pesho", 1, 2000);
        shop.getProduct().add(product);
        shop.getPercentMarkupByType().put(TypeProduct.FOOD, 10.0);
        shop.setLowerPriceIfDaysUntilExpIsUnder(5);
        shop.setPercentageIfCloserUntilExp(20);

        client = new Client( 1000);
        client.getCart().put(product, 2);

        Cashier cashier = new Cashier("Ana", 1, 1200);
        shop.getCheckout().put(1,cashier);

        cashierService = new CashierServiceImpl();
    }



@Test
void testProcessPurchaseSuccessful() {
    cashierService.processPurchase(client, shop, cashier, LocalDate.now());
    assertEquals(978.0, client.getMoney(), 0.01);
    assertEquals(0, product.getQuantity());
}

@Test
void testProcessPurchaseInsufficientFunds() {
    client.setMoney(1.0);
    assertThrows(RuntimeException.class, () ->
            cashierService.processPurchase(client, shop, cashier, LocalDate.now()));
}

@Test
void testProcessPurchaseProductNotFound() {
    Product nonExistentProduct = new Product(3, "none", 10, TypeProduct.FOOD, LocalDate.of(2026, 1, 1), 2);
    client.getCart().put(nonExistentProduct, 1);
    assertThrows(RuntimeException.class, () ->
            cashierService.processPurchase(client, shop, cashier, LocalDate.now()));
}

@Test
void testCalculateSellingPriceWithDiscount() {
    product.setExpirationDate(LocalDate.now().plusDays(3));
    double price = cashierService.calculateSellingPrice(shop, product, LocalDate.now());
    assertTrue(price < product.getDeliveryPrice() * 1.1);
}

@Test
void testCalculateSellingPriceWithoutDiscount() {
    product.setExpirationDate(LocalDate.now().plusDays(10));
    double price = cashierService.calculateSellingPrice(shop, product, LocalDate.now());
    assertEquals(product.getDeliveryPrice() * 1.1, price, 0.01);
}
@Test
    void testCalculateSellingPriceNormal() {
        double price = cashierService.calculateSellingPrice(shop, product, LocalDate.now());
        assertTrue(price > product.getDeliveryPrice());
    }

    @Test
    void testCalculateSellingPriceExpired() {
        product.setExpirationDate(LocalDate.now().minusDays(1));
        assertThrows(IllegalArgumentException.class, () ->
                cashierService.calculateSellingPrice(shop, product, LocalDate.now()));
    }
}
