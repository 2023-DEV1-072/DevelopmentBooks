package com.kata.developmentbooks.utils;

import com.kata.developmentbooks.model.Book;
import com.kata.developmentbooks.model.CartOrder;

import java.util.Arrays;
import java.util.List;

public class TestData {
    static Book cleanCode = new Book(0L,"Clean Code","Robert Martin","2008",50.0);
    static Book theCleanCoder = new Book(1L,"The Clean Coder","Robert Martin","2011",50.0);
    static Book cleanArchitecture = new Book(2L,"Clean Architecture","Robert Martin","2017",50.0);
    static Book tdd = new Book(3L,"Test Driven Development by Example","Robert Martin","2003",50.0);
    static Book legacyCode = new Book(4L,"Working Effectively With Legacy Code","Robert Martin","2004",50.0);
    private static List<CartOrder> onebook = Arrays.asList(new CartOrder(cleanCode,1));
    private static List<CartOrder> twobooks = Arrays.asList(new CartOrder(cleanCode,1),
            new CartOrder(theCleanCoder,1));
    private static List<CartOrder> threebooks = Arrays.asList(new CartOrder(cleanCode,1),
            new CartOrder(theCleanCoder,1),
            new CartOrder(cleanArchitecture,1));
    private static List<CartOrder> fourbooks = Arrays.asList(new CartOrder(cleanCode,1),
            new CartOrder(theCleanCoder,1),
            new CartOrder(cleanArchitecture,1),
            new CartOrder(tdd,1));
    private static List<CartOrder> fivebooks = Arrays.asList(new CartOrder(cleanCode,1),
            new CartOrder(theCleanCoder,1),
            new CartOrder(cleanArchitecture,1),
            new CartOrder(tdd,1),
            new CartOrder(legacyCode,1));

    public static List<CartOrder> getOneUniqueBook(){
        return onebook;
    }
    public static List<CartOrder> getTwoUniqueBooks(){
        return twobooks;
    }
    public static List<CartOrder> getThreeUniqueBooks(){
        return threebooks;
    }
    public static List<CartOrder> getFourUniqueBooks(){
        return fourbooks;
    }
    public static List<CartOrder> getFiveUniqueBooks(){
        return fivebooks;
    }
}
