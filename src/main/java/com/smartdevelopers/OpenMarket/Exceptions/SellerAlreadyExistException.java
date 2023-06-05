package com.smartdevelopers.OpenMarket.Exceptions;

public class SellerAlreadyExistException extends Exception
{
    public SellerAlreadyExistException()
    {
        super("Seller already exist");
    }
}
