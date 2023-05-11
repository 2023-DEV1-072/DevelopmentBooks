package com.kata.developmentbooks.model;

public class CartOrder {
    private Book book;
    private long quantity;
    public CartOrder(Book book,long quantity){
        this.book = book;
        this.quantity = quantity;
    }
    public Book getBook(){
        return book;
    }
    public long getQuantity(){
        return quantity;
    }
}
