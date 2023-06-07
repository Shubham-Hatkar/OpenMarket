package com.smartdevelopers.OpenMarket.Exceptions;

public class CustomerAlreadyExistException extends Exception{
    public CustomerAlreadyExistException()
    {
        super("Customer already exist");
    }
}
