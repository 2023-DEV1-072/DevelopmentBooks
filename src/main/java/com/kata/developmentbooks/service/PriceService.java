package com.kata.developmentbooks.service;

import com.kata.developmentbooks.model.CartOrder;
import org.springframework.stereotype.Service;
import com.kata.developmentbooks.model.FinalPriceSummary;

import java.util.List;
@Service
public class PriceService implements  IPriceService{
    @Override
    public FinalPriceSummary calculatePrice(List<CartOrder> bookList){
        FinalPriceSummary finalPriceSummary = new FinalPriceSummary(bookList,0);
        return finalPriceSummary;
    }
}
