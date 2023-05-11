package com.kata.developmentbooks.dao;

import com.kata.developmentbooks.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class BooksDao {
   List<Book> booksArray;
   public BooksDao(){
       booksArray = new ArrayList<Book>();
       Book cleanCode = new Book(0L,"Clean Code","Robert Martin","2008",50.0);
       Book theCleanCoder = new Book(1L,"The Clean Coder","Robert Martin","2011",50.0);
       Book cleanArchitecture = new Book(2L,"Clean Architecture","Robert Martin","2017",50.0);
       Book tdd = new Book(3L,"Test Driven Development by Example","Robert Martin","2003",50.0);
       Book legacyCode = new Book(4L,"Working Effectively With Legacy Code","Robert Martin","2004",50.0);

       booksArray.add(cleanCode);
       booksArray.add(theCleanCoder);
       booksArray.add(cleanArchitecture);
       booksArray.add(tdd);
       booksArray.add(legacyCode);
   }
   public  List<Book> getAllBooks(){

       return booksArray;
   }
}
