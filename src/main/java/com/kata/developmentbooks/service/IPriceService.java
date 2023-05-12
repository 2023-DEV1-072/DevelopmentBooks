package com.kata.developmentbooks.service;

import com.kata.developmentbooks.model.CartOrder;
import com.kata.developmentbooks.model.FinalPriceSummary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPriceService {
    public FinalPriceSummary calculatePrice(List<CartOrder> bookList);
    public boolean checkForInvalidBookQuantity(List<CartOrder> bookList);

}
