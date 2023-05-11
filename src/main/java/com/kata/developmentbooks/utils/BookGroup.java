package com.kata.developmentbooks.utils;

import com.kata.developmentbooks.model.Book;
import com.kata.developmentbooks.model.CartOrder;

import java.util.HashMap;
import java.util.List;

public class BookGroup {

    private BookGroup(){

    }

    public static HashMap<Long,CartOrder> spiltBooksToGroup(List<CartOrder> bookList){
        HashMap<Long,CartOrder> bookHashMap = new HashMap<Long,CartOrder>();
        bookList.forEach(c->{
            Book book = c.getBook();
            if(bookHashMap.get(book.getId())==null){
                bookHashMap.put(book.getId(),c);

            }else{
                CartOrder cartOrder =  bookHashMap.get(book.getId());
                bookHashMap.put(book.getId(),mergeCartOrders(c,cartOrder));
            }
        });
        return bookHashMap;
    }
    private static boolean isIdenticalCartOrders(CartOrder src,CartOrder des){
        return (src.getBook().getId()==des.getBook().getId());
    }
    private static CartOrder mergeCartOrders(CartOrder src,CartOrder des){

            return new CartOrder(src.getBook(),src.getQuantity()+des.getQuantity());

    }

}
