package com.kata.developmentbooks.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Book {
    private Long id;
    private String title;
    private String author;
    private String date;
    private Double price;
    public Book(Long id, String name, String author, String date,Double price) {
        this.id = id;
        this.title = name;
        this.author = author;
        this.date = date;
        this.price=price;
    }
    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
