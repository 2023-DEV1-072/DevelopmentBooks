package com.kata.developmentbooks.exception;

public class InvalidBookException extends Exception {
    public InvalidBookException(){
        super("InvalidBookException");
    }
    public InvalidBookException(String msg){
        super(msg);
    }
}
