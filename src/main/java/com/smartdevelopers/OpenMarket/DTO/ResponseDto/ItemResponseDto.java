package com.smartdevelopers.OpenMarket.DTO.ResponseDto;

import com.smartdevelopers.OpenMarket.Enum.ProductCategory;
import com.smartdevelopers.OpenMarket.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDto
{
    private String productName;
    private int price;
    private ProductCategory productCategory;
    private ProductStatus productStatus;
}
