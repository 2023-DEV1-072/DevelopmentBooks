package com.kata.developmentbooks.service;

import com.kata.developmentbooks.model.Book;
import com.kata.developmentbooks.model.CartOrder;
import com.kata.developmentbooks.utils.BookGroup;
import org.springframework.stereotype.Service;
import com.kata.developmentbooks.model.FinalPriceSummary;

import java.util.HashMap;
import java.util.List;
@Service
public class PriceService implements  IPriceService{
    private final float ONE_ITEM=0,
    TWO_ITEM=5,
    THREE_ITEM=15,
    FOUR_ITEM=20,
    FIVE_ITEM=50;
    @Override
    public FinalPriceSummary calculatePrice(List<CartOrder> bookList){

        HashMap<Long,CartOrder> bookHashMap = BookGroup.spiltBooksToGroup(bookList);
        FinalPriceSummary finalPriceSummary = new FinalPriceSummary(bookList,0,calculatePrice(bookHashMap));
        return finalPriceSummary;
    }

    private double calculatePrice(HashMap<Long,CartOrder> bookHashMap){

        switch(bookHashMap.size()){
            case 1:
                for (Long key:bookHashMap.keySet()) {
                    return bookHashMap.get(key).getQuantity()*bookHashMap.get(key).getBook().getPrice();
                }
                break;
            case 5:
                break;
            case 4:
                break;
            case 3:
                break;
            case 2:
                break;

            default:
        }
        return 0;
    }

}
