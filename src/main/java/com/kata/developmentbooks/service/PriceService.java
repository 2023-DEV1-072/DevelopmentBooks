package com.kata.developmentbooks.service;

import com.kata.developmentbooks.constants.Constants;
import com.kata.developmentbooks.exception.InvalidBookException;
import com.kata.developmentbooks.model.CartOrder;
import org.springframework.stereotype.Service;
import com.kata.developmentbooks.model.FinalPriceSummary;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PriceService implements  IPriceService{
    private final double ONE_ITEM=0.0,
    TWO_ITEM=5.0,
    THREE_ITEM=10.0,
    FOUR_ITEM=20.0,
    FIVE_ITEM=25.0;
    @Override
    public FinalPriceSummary calculatePrice(List<CartOrder> bookList){
        List<Long> allBooksIdsInCart = flattenHashMapToListOfBookIds(bookList);
        List<List<HashSet<Long>>> allPossibleCombinations = getAllPossibleCombinations(allBooksIdsInCart);
        double price = getLeastFinalPriceFromSets(allPossibleCombinations);
        FinalPriceSummary finalPriceSummary = new FinalPriceSummary(bookList, allBooksIdsInCart.size()*Constants.BOOK_PRICE,price);
        return finalPriceSummary;
    }

    public boolean checkForInvalidBookQuantity(List<CartOrder> bookList)  {

        for(CartOrder cart:bookList){
            if(cart.getQuantity()<=0)
                return true;
        }
        return false;
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

    private double calculateDiscountForGroup(int bookCountInGroup,double bookPrice) {

        double actualCost = bookCountInGroup * bookPrice;
        switch(bookCountInGroup){
            case 1: return bookPrice;
            case 2: return actualCost * (1 - (TWO_ITEM / 100));
            case 3: return actualCost * (1 - (THREE_ITEM / 100));
            case 4: return actualCost * (1 - (FOUR_ITEM / 100));
            case 5: return actualCost * (1 - (FIVE_ITEM / 100));
            default :
                return actualCost;
        }

    }


    private  List<List<HashSet<Long>>> getAllPossibleCombinations(List<Long>  allBooksInCart){
        List<List<HashSet<Long>>> allCombinations = new ArrayList<>();
        for(long i=Constants.BOOK_COUNT;i>0;i--){
            allCombinations.add(formSetsForGivenLimit((allBooksInCart),i));
        }
    return allCombinations;
    }
/*
    Form the possible combinations of book groups with given limit as size for the book group
*/
    private  List<HashSet<Long>> formSetsForGivenLimit(List<Long> allBooksInCart,long limit){
        List<HashSet<Long>> toReturn = new ArrayList<>();
       for(Long bookId: allBooksInCart){
           if(doesListHasElement(toReturn,bookId,limit) ){
               HashSet<Long> newBookSet = new HashSet<Long>();
               newBookSet.add(bookId);
               toReturn.add(newBookSet);
           }
           else
               for(HashSet<Long> set:toReturn)
                   if(!set.contains(bookId)&&set.size()<limit){
                       set.add(bookId);
                       break;
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
        for(HashSet<Long> set:list) {
            if (!set.contains(bookId) && set.size() < limit)
                hit = false;
        }
        return hit;
    }
    private List<Long> flattenHashMapToListOfBookIds( List<CartOrder> totalCart){
        List<Long> allBooks = new ArrayList<Long>();
        for (CartOrder cart:totalCart) {
            for (int i = 0; i < cart.getQuantity(); i++) {
                allBooks.add(cart.getBook().getId());
            }
        }

        return allBooks;
    }

}
