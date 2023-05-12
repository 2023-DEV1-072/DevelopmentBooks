package com.kata.developmentbooks.service;

import com.kata.developmentbooks.constants.Constants;
import com.kata.developmentbooks.model.Book;
import com.kata.developmentbooks.model.CartOrder;
import com.kata.developmentbooks.utils.BookGroup;
import org.springframework.stereotype.Service;
import com.kata.developmentbooks.model.FinalPriceSummary;

import java.util.*;
import java.util.stream.Collectors;

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
        List<List<HashSet<Long>>> allPossibleCombinations = getAllPossibleCombinations(bookHashMap);
        double price = getLeastFinalPriceFromSets(allPossibleCombinations);
        FinalPriceSummary finalPriceSummary = new FinalPriceSummary(bookList, getTotalBooksCountInCart(bookList)*Constants.BOOK_PRICE,price);
        return finalPriceSummary;
    }

    private double getLeastFinalPriceFromSets(List<List<HashSet<Long>>> allPossibleCombinations){

        List<Double> allPriceForCombinations = allPossibleCombinations.stream().map(eachCombination-> {
            double priceForOnePossibleCombination =0;
            for (HashSet<Long> eachBookSet : eachCombination) {

                priceForOnePossibleCombination+=calculateDiscountForGroup(eachBookSet.size(),Constants.BOOK_PRICE);
            }
            return priceForOnePossibleCombination;
        }).collect(Collectors.toList());
        double min = allPriceForCombinations.stream().min(Comparator.naturalOrder()).get();
        return min;
    }

    private long getTotalBooksCountInCart(List<CartOrder> bookList){
        long booksCount =0l;
        for (CartOrder cartOder:bookList
             ) {
            booksCount+=cartOder.getQuantity();
        }
        return booksCount;
    }

    private double calculateDiscountForGroup(int bookCountInGroup,double bookPrice) {

        double actualCost = bookCountInGroup * bookPrice;
        switch(bookCountInGroup){
            case 1: return bookPrice;
            case 2: return actualCost * (1 - (5.0 / 100));
            case 3: return actualCost * (1 - (10.0 / 100));
            case 4: return actualCost * (1 - (20.0 / 100));
            case 5: return actualCost * (1 - (25.0 / 100));
            default :
                return 0;
        }

    }

    private List<HashMap<Long,Long>> addBookToGroup(List<HashMap<Long,Long>> bookGroup,Book book){
        for (HashMap<Long,Long> bookMap: bookGroup) {
            if(!bookMap.containsKey(book.getId())){
                bookMap.put(book.getId(),1l);
                break;
            }
        }
        return bookGroup;
    }


    private  List<List<HashSet<Long>>> getAllPossibleCombinations(HashMap<Long,CartOrder> allBooksInCart){
        List<List<HashSet<Long>>> allCombinations = new ArrayList<>();
        for(long i=Constants.BOOK_COUNT;i>0;i--){
            allCombinations.add(formSetsForGivenLimit(flattenHashMapToListOfBookIds(allBooksInCart),i));
        }
    return allCombinations;
    }

    private  List<HashSet<Long>> formSetsForGivenLimit(List<Long> allBooksInCart,long limit){
        List<HashSet<Long>> toReturn = new ArrayList<>();
       for(Long bookId: allBooksInCart){


           if(doesListHasElement(toReturn,bookId,limit) ){
               HashSet<Long> newBookSet = new HashSet<Long>();
               newBookSet.add(bookId);
               toReturn.add(newBookSet);

           }else{
               for(HashSet<Long> set:toReturn){
                   if(!set.contains(bookId)&&set.size()<limit){
                       set.add(bookId);
                       break;
                   }
               }
           }
       }
       return toReturn;
    }
    /*
       for a given bookid and list of book sets
            check if all the book sets in list has the limit reached
            (or)
            it already has the element
     */
    private boolean doesListHasElement(List<HashSet<Long>> list,long bookId,long limit){
        boolean hit = true;
        for(HashSet<Long> set:list){
            if(!set.contains(bookId)&&set.size()<limit){
                hit = false;
            }
        }
        return hit;
    }
    private List<Long> flattenHashMapToListOfBookIds( HashMap<Long,CartOrder> allBooksInCart){
        List<Long> allBooks = new ArrayList<Long>();
        for (Map.Entry<Long, CartOrder> set :
                allBooksInCart.entrySet()) {
            set.getValue();
            for(int i=0;i<set.getValue().getQuantity();i++)
                allBooks.add(set.getKey());
        }
        return allBooks;
    }

}
