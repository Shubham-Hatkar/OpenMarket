package com.smartdevelopers.OpenMarket.Controller;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.SellerRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.SellerResponseDto;
import com.smartdevelopers.OpenMarket.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController
{

    @Autowired
    SellerService sellerService;

    // Add seller
    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto)
    {
        try
        {
            sellerService.addSeller(sellerRequestDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Seller registered successfully.";
    }

    // Get all sellers
    @GetMapping("/allsellers")
    public ResponseEntity getAllSellers()
    {
        List<SellerResponseDto> sellers = sellerService.getAllSellers();
        return new ResponseEntity(sellers, HttpStatus.FOUND);
    }

    // Get seller by aadharNo
    // Find seller of a particular age
}
