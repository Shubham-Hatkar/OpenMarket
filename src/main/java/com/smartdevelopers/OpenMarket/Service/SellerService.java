package com.smartdevelopers.OpenMarket.Service;

import com.smartdevelopers.OpenMarket.Convertors.SellerConvertor;
import com.smartdevelopers.OpenMarket.DTO.RequestDto.SellerRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.SellerResponseDto;
import com.smartdevelopers.OpenMarket.Exceptions.SellerAlreadyExistException;
import com.smartdevelopers.OpenMarket.Model.Seller;
import com.smartdevelopers.OpenMarket.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService
{
    @Autowired
    SellerRepository sellerRepository;

    public void addSeller(SellerRequestDto sellerRequestDto) throws SellerAlreadyExistException
    {
//        Seller seller = new Seller();
//        seller.setName(addSellerRequestDto.getName());
//        seller.setEmail(addSellerRequestDto.getEmail());
//        seller.setAadharNo(addSellerRequestDto.getAadharNo());
//        seller.setMobNo(addSellerRequestDto.getMobNo());

//        Seller seller = Seller.builder()
//                .name(addSellerRequestDto.getName())
//                .email(addSellerRequestDto.getEmail())
//                .mobNo(addSellerRequestDto.getMobNo())
//                .aadharNo(addSellerRequestDto.getAadharNo())
//                .name(addSellerRequestDto.getName())
//                .build();

        Seller seller = SellerConvertor.SellerRequestDtoToSeller(sellerRequestDto);

        try
        {
            sellerRepository.save(seller);
        }
        catch (Exception e)
        {
            throw new SellerAlreadyExistException();
        }
    }


    public List<SellerResponseDto> getAllSellers()
    {
        List<Seller> sellerList = sellerRepository.findAll();
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller seller : sellerList)
        {
            SellerResponseDto sellerResponseDto = SellerConvertor.sellerToSellerResponseDto(seller);
            sellerResponseDtoList.add(sellerResponseDto);
        }
        return sellerResponseDtoList;
    }
}
