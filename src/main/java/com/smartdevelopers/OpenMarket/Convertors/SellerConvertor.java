package com.smartdevelopers.OpenMarket.Convertors;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.AddSellerRequestDto;
import com.smartdevelopers.OpenMarket.Model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConvertor
{
    public static Seller SellerRequestDtoToSeller(AddSellerRequestDto addSellerRequestDto)
    {
        Seller seller = Seller.builder()
                .name(addSellerRequestDto.getName())
                .email(addSellerRequestDto.getEmail())
                .mobNo(addSellerRequestDto.getMobNo())
                .aadharNo(addSellerRequestDto.getAadharNo())
                .name(addSellerRequestDto.getName())
                .build();

        return seller;
    }
}
