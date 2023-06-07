package com.smartdevelopers.OpenMarket.Convertors;

import com.smartdevelopers.OpenMarket.DTO.ResponseDto.ItemResponseDto;
import com.smartdevelopers.OpenMarket.Model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemConvertor
{
    public static ItemResponseDto productToItemResponseDto(Product product)
    {
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .build();
        return itemResponseDto;
    }

}
