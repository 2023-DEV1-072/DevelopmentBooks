package com.kata.developmentbooks.model;

import java.util.List;

public class FinalPriceSummary {
    private List<CartOrder> booksOrder;
    private double finalPrice;
    private double initialPrice;
    public FinalPriceSummary(List<CartOrder> booksOrder,double initialPrice, double finalPrice){
        this.booksOrder = booksOrder;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
    }

    public List<CartOrder> getBooksOrder() {
        return booksOrder;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
    public double getInitialPrice() {
        return initialPrice;
    }
}
