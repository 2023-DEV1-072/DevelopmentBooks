package com.kata.developmentbooks.model;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class CartOrder {
    private Book book;
    private long quantity;
    public CartOrder(Book book,long quantity){
        this.book = book;
        this.quantity = quantity;
    }
}
