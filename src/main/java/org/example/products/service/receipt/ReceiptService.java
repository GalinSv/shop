package org.example.products.service.receipt;

import org.example.products.data.Receipt;

import java.io.IOException;

public interface ReceiptService {
    void printAndSave(Receipt receipt) throws IOException;

}
