package com.smartdevelopers.OpenMarket.Convertors;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.AddProductRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.AddProductResponseDto;
import com.smartdevelopers.OpenMarket.Enum.ProductStatus;
import com.smartdevelopers.OpenMarket.Model.Product;
import com.smartdevelopers.OpenMarket.Model.Seller;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConvertor
{
    public Product addProductRequestToSeller(AddProductRequestDto addProductRequestDto)
    {
        Product product = Product.builder()
                .name(addProductRequestDto.getName())
                .price(addProductRequestDto.getPrice())
                .quantity(addProductRequestDto.getQuantity())
                .productCategory(addProductRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();

        return product;
    }

    public static AddProductResponseDto productToProductResponseDto(Product product)
    {
        AddProductResponseDto addProductResponseDto = AddProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();

        return addProductResponseDto;
    }
}
