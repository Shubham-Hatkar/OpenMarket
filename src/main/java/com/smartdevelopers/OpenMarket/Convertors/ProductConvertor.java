package com.smartdevelopers.OpenMarket.Convertors;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.ProductRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.ProductResponseDto;
import com.smartdevelopers.OpenMarket.Enum.ProductStatus;
import com.smartdevelopers.OpenMarket.Model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConvertor
{
    public Product addProductRequestToSeller(ProductRequestDto productRequestDto)
    {
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();

        return product;
    }

    public static ProductResponseDto productToProductResponseDto(Product product)
    {
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();

        return productResponseDto;
    }
}
