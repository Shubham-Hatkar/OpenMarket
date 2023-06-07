package com.smartdevelopers.OpenMarket.Service;

import com.smartdevelopers.OpenMarket.Convertors.ItemConvertor;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.ItemResponseDto;
import com.smartdevelopers.OpenMarket.Exceptions.ProductNotFoundException;
import com.smartdevelopers.OpenMarket.Model.Item;
import com.smartdevelopers.OpenMarket.Model.Product;
import com.smartdevelopers.OpenMarket.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService
{
    @Autowired
    ProductRepository productRepository;

    public ItemResponseDto viewItem(int productId) throws ProductNotFoundException
    {
        Product product;
        try
        {
            product = productRepository.findById(productId).get();
        }
        catch (Exception e)
        {
            throw new ProductNotFoundException();
        }


        Item item = Item.builder()
                .requiredQuantity(0)
                .product(product)
                .build();
        // map item-product
        product.setItem(item);
        // save product and item
        productRepository.save(product);

        ItemResponseDto itemResponseDto = ItemConvertor.productToItemResponseDto(product);
        return itemResponseDto;
    }
}
