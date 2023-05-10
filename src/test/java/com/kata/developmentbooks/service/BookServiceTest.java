package com.kata.developmentbooks.service;

import com.kata.developmentbooks.controller.BookController;
import com.kata.developmentbooks.model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = BookController.class)
@ExtendWith(SpringExtension.class)
public class BookServiceTest{
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;
    @Test
    @DisplayName("validate catalogue of the  books is array and count is 5")
    void getAllBooksTest() throws Exception {

        mockMvc.perform(get("/api/books"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$",hasSize(5)));

        verify(bookService).getAllBooks();
    }


}
