package org.example.products.data;

import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class Shop {

    private List<Product> product;

    private EnumMap<TypeProduct,Double> percentMarkupByType;
    private Map<Integer, Cashier> checkout;
    private int lowerPriceIfDaysUntilExpIsUnder;
    private double percentageIfCloserUntilExp;


    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
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

    public Shop(String name) {

        this.percentMarkupByType = new EnumMap<>(TypeProduct.class);
        this.product = new LinkedList<>();
        this.checkout = new LinkedHashMap<>();
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

    public void setCheckout(Map<Integer, Cashier> checkout) {
        this.checkout = checkout;
    }
}
