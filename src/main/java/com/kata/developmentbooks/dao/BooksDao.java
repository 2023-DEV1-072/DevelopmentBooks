package com.kata.developmentbooks.dao;

import com.kata.developmentbooks.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class BooksDao {
   List<Book> booksArray = new ArrayList<Book>();
   public  List<Book> getAllBooks(){
       return booksArray;
   }
}
