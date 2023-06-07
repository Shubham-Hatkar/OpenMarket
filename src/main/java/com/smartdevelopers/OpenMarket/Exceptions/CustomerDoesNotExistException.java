package com.smartdevelopers.OpenMarket.Exceptions;

public class CustomerDoesNotExistException extends Exception{
    public CustomerDoesNotExistException()
    {
        super("Customer does not exist");
    }
}
