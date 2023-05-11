package com.kata.developmentbooks.model;

import java.util.List;

public class FinalPriceSummary {
    private List<CartOrder> booksOrder;
    private double amount;
    public FinalPriceSummary(List<CartOrder> booksOrder, double amount){
        this.booksOrder = booksOrder;
        this.amount=amount;
    }

    public List<CartOrder> getBooksOrder() {
        return booksOrder;
    }

    public double getAmount() {
        return amount;
    }
}
