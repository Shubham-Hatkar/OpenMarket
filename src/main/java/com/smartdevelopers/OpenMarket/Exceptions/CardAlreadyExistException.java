package com.smartdevelopers.OpenMarket.Exceptions;

public class CardAlreadyExistException extends Exception{
    public CardAlreadyExistException()
    {
        super("Duplicate Card Number");
    }
}
