package com.kata.developmentbooks.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class FinalPriceSummary {
    private List<CartOrder> booksOrder;
    private double finalPrice;
    private double initialPrice;
    public FinalPriceSummary(List<CartOrder> booksOrder,double initialPrice, double finalPrice){
        this.booksOrder = booksOrder;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
    }
}
