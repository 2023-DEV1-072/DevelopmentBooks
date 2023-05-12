package com.kata.developmentbooks.service;

import com.kata.developmentbooks.model.FinalPriceSummary;
import com.kata.developmentbooks.utils.TestData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceServiceTest {
    @Autowired
    PriceService priceService;
    @BeforeEach
    public void init() {

    }

    @Test
    @DisplayName("validate price of one book to be 50.0")
    void calculatePriceForOneItem() throws Exception {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getOneUniqueBook());
       assertEquals(50d, finalPriceSummary.getFinalPrice());
    }
    @Test
    @DisplayName("validate price of two books to be 95.0")
    void calculatePriceForTwoItems() throws Exception {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getTwoUniqueBooks());
        assertEquals(95d, finalPriceSummary.getFinalPrice());
    }
    @Test
    @DisplayName("validate price of three books to be 135.0")
    void calculatePriceForThreeItems() throws Exception {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getThreeUniqueBooks());
        assertEquals(135d, finalPriceSummary.getFinalPrice());
    }
    @Test
    @DisplayName("validate price of four books to be 160.0")
    void calculatePriceForFourItems() throws Exception {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getFourUniqueBooks());
        assertEquals(160d, finalPriceSummary.getFinalPrice());
    }
    @Test
    @DisplayName("validate price of five books to be 187.5")
    void calculatePriceForFiveItem() throws Exception {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getFiveUniqueBooks());
        assertEquals(187.5d, finalPriceSummary.getFinalPrice());
    }

    @Test
    @DisplayName("validate price of two books of same category to be 150.0")
    void calculatePriceForTwoIdenticalItems() throws Exception {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getIdenticalBooks(3));
        assertEquals(150.0d, finalPriceSummary.getFinalPrice());
    }
    @Test
    @DisplayName("validate price of different group for optimised discount")
    void calculatePriceForOptimalDiscount() throws Exception {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getOptimalBooksCartList());
        assertEquals(320.0d, finalPriceSummary.getFinalPrice());
    }




}
