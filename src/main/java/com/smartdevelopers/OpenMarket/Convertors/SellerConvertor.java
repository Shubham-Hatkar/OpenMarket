package com.smartdevelopers.OpenMarket.Convertors;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.SellerRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.SellerResponseDto;
import com.smartdevelopers.OpenMarket.Model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConvertor
{
    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto)
    {
        Seller seller = Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .mobNo(sellerRequestDto.getMobNo())
                .aadharNo(sellerRequestDto.getAadharNo())
                .name(sellerRequestDto.getName())
                .build();

        return seller;
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller)
    {
        SellerResponseDto sellerResponseDto = SellerResponseDto.builder()
                .name(seller.getName())
                .email(seller.getEmail())
                .mobNo(seller.getMobNo())
                .build();
        return sellerResponseDto;
    }
}
