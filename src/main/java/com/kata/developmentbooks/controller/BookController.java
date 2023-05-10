package com.kata.developmentbooks.controller;

import com.kata.developmentbooks.model.Book;
import com.kata.developmentbooks.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private IBookService bookService;
    @GetMapping("/books")
    public List<Book> getBooks(){
        List<Book> books = bookService.getAllBooks();
        return books;
    }
}
