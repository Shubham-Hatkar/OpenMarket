package com.smartdevelopers.OpenMarket.Exceptions;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException()
    {
        super("Product not found");
    }
}
