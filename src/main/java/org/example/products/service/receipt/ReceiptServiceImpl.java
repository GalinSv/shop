package org.example.products.service.receipt;

import org.example.products.data.Receipt;

import java.io.*;

public class ReceiptServiceImpl implements  ReceiptService{

    @Override
    public void printAndSave(Receipt receipt) throws IOException{
        if (receipt == null) {
            throw new IllegalArgumentException("Cannot save a null receipt.");
        }

        String txtFilename = "receipt_" + receipt.getId() + ".txt";
        String serFilename = "receipt_" + receipt.getId() + ".ser";

        try (PrintWriter writer = new PrintWriter(txtFilename)) {
            writer.println("Receipt ID: " + receipt.getId());
            writer.println("Cashier: " + receipt.getCashier().getName());
            writer.println("Date: " + receipt.getDateTime());
            writer.println("Items:");
            for (var entry : receipt.getItems().entrySet()) {
                writer.printf("- %s x%d = %.2f%n",
                        entry.getKey().getName(),
                        entry.getValue(),
                        entry.getKey().getSellPrice() * entry.getValue());
            }
            writer.printf("Total: %.2f%n", receipt.getTotal());
        } catch (IOException e) {
            throw new IOException("Failed to write receipt to text file: " + txtFilename, e);
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(serFilename))) {
            out.writeObject(receipt);
        } catch (IOException e) {
            throw new IOException("Failed to serialize receipt to file: " + serFilename, e);
        }
    }

}

