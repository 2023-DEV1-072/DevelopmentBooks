package com.kata.developmentbooks.service;

import com.kata.developmentbooks.dao.BooksDao;
import com.kata.developmentbooks.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService implements  IBookService{
    @Autowired
    BooksDao booksDao;
    @Override
    public List<Book> getAllBooks() {
        return booksDao.getAllBooks();
    }


}
