package com.kata.developmentbooks.service;

import com.kata.developmentbooks.constants.BooksEnum;
import com.kata.developmentbooks.model.Book;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BookService implements  IBookService{
    @Override
    public List<Book> getAllBooks() {
        return BooksEnum.getAllBooks();
    }
}
