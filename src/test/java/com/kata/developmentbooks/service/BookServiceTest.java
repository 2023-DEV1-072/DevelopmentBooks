package com.kata.developmentbooks.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
    private final int BOOK_COUNT=5;
    @BeforeEach
    public void init() {

    }
    @Autowired
    BookService bookService;
    @Test
    @DisplayName("validate catalogue of the  books count is 5")
    void getAllBooksTest() throws Exception {
        assertEquals(BOOK_COUNT,bookService.getAllBooks().size());
    }
    @Test
    @DisplayName("validate for no duplicate values in books catalogue")
    void checkForDuplicateEntries() throws Exception {
        HashSet<Long> uniqueIDSet = new HashSet<Long>();
        bookService.getAllBooks().forEach(b->uniqueIDSet.add(b.getId()));
        assertEquals(BOOK_COUNT,uniqueIDSet.size());
    }
}
