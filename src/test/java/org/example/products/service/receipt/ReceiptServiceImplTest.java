package org.example.products.service.receipt;

import org.example.products.data.Cashier;
import org.example.products.data.Product;
import org.example.products.data.Receipt;
import org.example.products.data.TypeProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptServiceImplTest {

    private ReceiptServiceImpl receiptService;
    private Receipt receipt;
    private String txtFilename;
    private String serFilename;

    @BeforeEach
    void setUp() {
        receiptService = new ReceiptServiceImpl();

        Product product = new Product(1, "Apple", 2,  TypeProduct.FOOD, LocalDate.now().plusDays(5),0);
        product.setSellPrice(2.5);
        Cashier cashier = new Cashier("John", 1, 1500);
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(product, 3);

        receipt = new Receipt(cashier, cart, 70);
        txtFilename = "receipt_" + receipt.getId() + ".txt";
        serFilename = "receipt_" + receipt.getId() + ".ser";
    }

    @Test
    void testPrintAndSaveValidReceiptCreatesFiles() throws IOException {
        receiptService.printAndSave(receipt);

        assertTrue(new File(txtFilename).exists(), "File created");
        assertTrue(new File(serFilename).exists(), "Serialized file created");
    }

    @Test
    void testPrintAndSaveNullReceiptThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> receiptService.printAndSave(null));
        assertEquals("Cannot save a null receipt", ex.getMessage());
    }

    @Test
    void testPrintAndSaveInvalidPathThrowsIOException() {
        ReceiptServiceImpl rec = new ReceiptServiceImpl() {
            @Override
            public void printAndSave(Receipt receipt) throws IOException {
                String path = "/invalid/receipt.txt";
                try (PrintWriter writer = new PrintWriter(path)) {
                    writer.println("fail");
                }
            }
        };

        assertThrows(IOException.class, () -> rec.printAndSave(receipt));
    }
}