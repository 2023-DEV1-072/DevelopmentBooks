package com.kata.developmentbooks.constants;

import com.kata.developmentbooks.model.Book;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public enum BooksEnum {
        CLEAN_CODE(0, "Clean Code", "Robert Martin", "2008", 50.00),
        THE_CLEAN_CODER(1, "The Clean Coder", "Robert Martin", "2011", 50.00),
        CLEAN_ARCHITECTURE(2, "Clean Architecture", "Robert Martin", "2017", 50.00),
        TEST_DRIVEN_DEVELOPMENT(3, "Test-Driven Development By Example", "Kent Beck", "2003", 50.00),
        WORKING_WITH_LEGACY_CODE(4, "Working Effectively With Legacy Code", "Michael C. Feathers", "2004", 50.00);
        private long id;
        private String title;
        private String author;
        private String date;
        private double price;
        BooksEnum(long id, String title, String author, String date, double price) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.date = date;
            this.price = price;
        }
    public static List<Book> getAllBooks() {
        return Arrays.stream(BooksEnum.values())
                .map(book -> new Book(book.id, book.title, book.author ,book.date,book.price))
                .collect(Collectors.toList());
    }
}
