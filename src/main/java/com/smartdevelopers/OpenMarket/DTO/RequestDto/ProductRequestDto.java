package com.smartdevelopers.OpenMarket.DTO.RequestDto;

import com.smartdevelopers.OpenMarket.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto
{
    private String name;
    private int price;
    private int quantity;
    private ProductCategory productCategory;
    private int sellerId;
}
