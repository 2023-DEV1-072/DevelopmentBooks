package com.kata.developmentbooks.service;

import com.kata.developmentbooks.model.FinalPriceSummary;
import com.kata.developmentbooks.utils.TestData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceServiceTest {
    PriceService priceService;
    @BeforeEach
    public void init() {
        priceService  = new PriceService();
    }
    @Test
    @DisplayName("validate price of one book to be 50.0")
    void calculatePriceForOneItem() {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getOneUniqueBook());
        assertEquals(50d, finalPriceSummary.getFinalPrice());
        assertEquals(50d, finalPriceSummary.getInitialPrice());
    }
    @Test
    @DisplayName("validate price of two books to be 95.0")
    void calculatePriceForTwoItems()  {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getTwoUniqueBooks());
        assertEquals(95d, finalPriceSummary.getFinalPrice());
        assertEquals(100d, finalPriceSummary.getInitialPrice());
    }
    @Test
    @DisplayName("validate price of three books to be 135.0")
    void calculatePriceForThreeItems()  {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getThreeUniqueBooks());
        assertEquals(135d, finalPriceSummary.getFinalPrice());
        assertEquals(150d, finalPriceSummary.getInitialPrice());
    }
    @Test
    @DisplayName("validate price of four books to be 160.0")
    void calculatePriceForFourItems()  {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getFourUniqueBooks());
        assertEquals(160d, finalPriceSummary.getFinalPrice());
        assertEquals(200d, finalPriceSummary.getInitialPrice());
    }
    @Test
    @DisplayName("validate price of five books to be 187.5")
    void calculatePriceForFiveItem()  {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getFiveUniqueBooks());
        assertEquals(187.5d, finalPriceSummary.getFinalPrice());
        assertEquals(250d, finalPriceSummary.getInitialPrice());
    }
    @Test
    @DisplayName("validate price of two books of same category to be 150.0")
    void calculatePriceForTwoIdenticalItems() throws Exception {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getIdenticalBooks(3));
        assertEquals(150.0d, finalPriceSummary.getFinalPrice());
        assertEquals(150.0d, finalPriceSummary.getInitialPrice());
    }
    @Test
    @DisplayName("validate price of different group for optimised discount")
    void calculatePriceForOptimalDiscount() throws Exception {
        FinalPriceSummary finalPriceSummary = priceService.calculatePrice(TestData.getOptimalBooksCartList());
        assertEquals(320.0d, finalPriceSummary.getFinalPrice());
        assertEquals(400.0d, finalPriceSummary.getInitialPrice());
    }

    @Test
    @DisplayName("validate books request for invalid zero quantity to be true")
    void calculatePriceZeroCartOfBooks()  {
        assertEquals(true, priceService.checkForInvalidBookQuantity(TestData.getIdenticalBooks(0)));

    }
    @Test
    @DisplayName("validate books request for invalid negative quantity to be true")
    void calculatePriceNegativeCartOfBooks()  {
        assertEquals(true, priceService.checkForInvalidBookQuantity(TestData.getIdenticalBooks(-5)));

    }

}
