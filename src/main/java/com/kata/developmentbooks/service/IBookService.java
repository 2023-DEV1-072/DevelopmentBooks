package com.kata.developmentbooks.service;

import com.kata.developmentbooks.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookService {
        List<Book> getAllBooks();
}
