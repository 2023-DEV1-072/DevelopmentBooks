package com.kata.developmentbooks.controller;

import com.google.gson.Gson;
import com.kata.developmentbooks.utils.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    @DisplayName("validate catalogue of the  books is array and count is 5")
    void getAllBooksTest() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$",hasSize(5)));
    }
    @Test
    @DisplayName("validate price of two books in a set is 95")
    void getPriceForTwoUniqueBooksTest() throws Exception {
        String json = ObjectToJson(TestData.getTwoUniqueBooks());
        mockMvc.perform(post("/api/books/calculateBookPrice")
                .contentType("application/json")
                .content(json))
                .andExpect(jsonPath("$.finalPrice").value(95.0))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("validate request  of books for zero order quantity to be 400 error series")
    void getPriceForZeroBookSize() throws Exception {
        String json = ObjectToJson(TestData.getIdenticalBooks(0));
        mockMvc.perform(post("/api/books/calculateBookPrice")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("validate request  of books for negative order quantity to be 400 error series")
    void getPriceForNegativeBookSize() throws Exception {
        String json = ObjectToJson(TestData.getIdenticalBooks(-1));
        mockMvc.perform(post("/api/books/calculateBookPrice")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().is4xxClientError());
    }
    private String ObjectToJson(Object toConvert){
        Gson gson = new Gson();
        String json = gson.toJson(toConvert);
        return json;
    }
}
