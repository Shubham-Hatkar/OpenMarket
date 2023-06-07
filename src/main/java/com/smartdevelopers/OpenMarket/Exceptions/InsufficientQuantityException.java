package com.smartdevelopers.OpenMarket.Exceptions;

public class InsufficientQuantityException extends Exception{
    public InsufficientQuantityException()
    {
        super("Insufficient quantities");
    }
}
