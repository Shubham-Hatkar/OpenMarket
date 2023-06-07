package com.smartdevelopers.OpenMarket.DTO.ResponseDto;

import com.smartdevelopers.OpenMarket.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto
{
    private String name;
    private int price;
    private int quantity;
    private ProductStatus productStatus;
}
