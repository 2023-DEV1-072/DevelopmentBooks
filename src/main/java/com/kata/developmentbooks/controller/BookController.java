package com.kata.developmentbooks.controller;

import com.kata.developmentbooks.constants.Constants;
import com.kata.developmentbooks.model.Book;
import com.kata.developmentbooks.model.CartOrder;
import com.kata.developmentbooks.service.IBookService;
import com.kata.developmentbooks.service.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private IBookService bookService;
    @Autowired
    private IPriceService priceService;
    @GetMapping(path = "/books",produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Book> getBooks(){
        List<Book> books = bookService.getAllBooks();
        return books;
    }
    @PostMapping(path = "/books/calculateBookPrice", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> calculateBooksPrice(@RequestBody List<CartOrder> booksOrder) {
    if(priceService.checkForInvalidBookQuantity(booksOrder)){
        return ResponseEntity.badRequest().body(Constants.INVALID_BOOK_QUANTITY);
    }
        return ResponseEntity.ok(priceService.calculatePrice(booksOrder));
    }
}
