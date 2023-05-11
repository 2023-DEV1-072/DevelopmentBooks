package com.kata.developmentbooks.service;

import com.kata.developmentbooks.constants.Constants;
import com.kata.developmentbooks.model.Book;
import com.kata.developmentbooks.model.CartOrder;
import com.kata.developmentbooks.utils.BookGroup;
import org.springframework.stereotype.Service;
import com.kata.developmentbooks.model.FinalPriceSummary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<HashMap<Long,Long>> groups = sortBooksToGroups(bookHashMap);
        FinalPriceSummary finalPriceSummary = new FinalPriceSummary(bookList,0,calculatePriceForCart(groups));
        return finalPriceSummary;
    }

    private double calculatePriceForCart( List<HashMap<Long,Long>> groups){
       double finalPrice =0;
        for (HashMap<Long,Long> eachGroup:groups) {

                finalPrice+=calculateDiscountForGroup(eachGroup.size(), Constants.BOOK_PRICE);
        }
        return finalPrice;
    }
    private double calculateDiscountForGroup(int bookCount,double bookPrice) {

        double actualCost = bookCount * bookPrice;
        switch(bookCount){
            case 1: return bookPrice;
            case 2: return actualCost * (1 - (5.0 / 100));
            case 3: return actualCost * (1 - (10.0 / 100));
            case 4: return actualCost * (1 - (20.0 / 100));
            case 5: return actualCost * (1 - (25.0 / 100));
            default :
                return 0;
        }

    }

    private List<HashMap<Long,Long>> sortBooksToGroups(HashMap<Long,CartOrder> bookHashMap){
        List<HashMap<Long,Long>> bookGroups =  initiateListofGroups(getBiggestBookOrder(bookHashMap));
        for (Map.Entry<Long, CartOrder> map : bookHashMap.entrySet()) {
            Book book =  map.getValue().getBook();
            long quantity = map.getValue().getQuantity();
            for(int i=0;i<quantity;i++){
                bookGroups = addBookToGroup(bookGroups,book);
            }
        }
        return bookGroups;
    }
    private List<HashMap<Long,Long>> addBookToGroup(List<HashMap<Long,Long>> bookGroup,Book book){
        for (HashMap<Long,Long> bookMap: bookGroup) {
            if(bookMap.get(book.getId())==null){
                bookMap.put(book.getId(),1l);
                break;
            }
        }
        return bookGroup;
    }
    private List<HashMap<Long,Long>> initiateListofGroups(long size){
        List<HashMap<Long,Long>> bookGroups =  new ArrayList<>();
        for(int i=0;i<size;i++){
            bookGroups.add(new HashMap<Long,Long>());
        }
        return bookGroups;
    }
    private long getBiggestBookOrder(HashMap<Long,CartOrder> bookHashMap){
        long biggest=0;

        for (Map.Entry<Long, CartOrder> map : bookHashMap.entrySet()) {
            long quantity = map.getValue().getQuantity();
            if(biggest<quantity){
                biggest = quantity;
            }
        }
        return biggest;
    }
}
