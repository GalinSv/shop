package org.example.products.data;

import java.time.LocalDate;
import java.util.EnumMap;

public class Product {
    private long id;
    private int quantity;
    private String name;
    private double deliveryPrice;

    private LocalDate expirationDate;

    private TypeProduct typeProduct;
    private double sellPrice;

    public Product(long id, String name, double deliveryPrice, TypeProduct typeProduct, LocalDate expirationDate, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.typeProduct = typeProduct;
        this.expirationDate = expirationDate;
    }
    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryPrice=" + deliveryPrice +
                ", expirationDate=" + expirationDate +
                ", typeProduct=" + typeProduct +
                '}';
    }

}
