package org.example.products.data;

import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class Shop {

    private Set<Product> product;

    private EnumMap<TypeProduct,Double> percentMarkupByType;
    private Map<Integer, Cashier> checkout;
    private int lowerPriceIfDaysUntilExpIsUnder;
    private double percentageIfCloserUntilExp;
    private Set<Product> soldProducts;
    private Set<Receipt> issuedReceipts;
    public Shop() {

        this.percentMarkupByType = new EnumMap<>(TypeProduct.class);
        this.product = new HashSet<>();
        this.checkout = new HashMap<>();
        this.soldProducts = new HashSet<>();
        this.issuedReceipts = new HashSet<>();
    }

    public void setCheckout(Map<Integer, Cashier> checkout) {
        this.checkout = checkout;
    }

    public Set<Product> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Set<Receipt> getIssuedReceipts() {
        return issuedReceipts;
    }

    public void setIssuedReceipts(Set<Receipt> issuedReceipts) {
        this.issuedReceipts = issuedReceipts;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Shop{" +

                ", product=" + product +
                ", percentMarkupByType=" + percentMarkupByType +
                ", checkout=" + checkout +
                ", lowerPriceIfDaysUntilExpIsUnder=" + lowerPriceIfDaysUntilExpIsUnder +
                ", percentageIfCloserUntilExp=" + percentageIfCloserUntilExp +
                '}';
    }


    public int getLowerPriceIfDaysUntilExpIsUnder() {
        return lowerPriceIfDaysUntilExpIsUnder;
    }

    public void setLowerPriceIfDaysUntilExpIsUnder(int lowerPriceIfDaysUntilExpIsUnder) {
        this.lowerPriceIfDaysUntilExpIsUnder = lowerPriceIfDaysUntilExpIsUnder;
    }

    public double getPercentageIfCloserUntilExp() {
        return percentageIfCloserUntilExp;
    }

    public void setPercentageIfCloserUntilExp(double percentageIfCloserUntilExp) {
        this.percentageIfCloserUntilExp = percentageIfCloserUntilExp;
    }


    public EnumMap<TypeProduct, Double> getPercentMarkupByType() {
        return percentMarkupByType;
    }

    public void setPercentMarkupByType(EnumMap<TypeProduct, Double> percentMarkupByType) {
        this.percentMarkupByType = percentMarkupByType;
    }

    public Map<Integer, Cashier> getCheckout() {
        return checkout;
    }


}
