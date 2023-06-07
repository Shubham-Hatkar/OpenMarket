package com.smartdevelopers.OpenMarket.Exceptions;

public class CardDoesNotExistException extends Exception{
    public CardDoesNotExistException()
    {
        super("Card does not found");
    }
}
