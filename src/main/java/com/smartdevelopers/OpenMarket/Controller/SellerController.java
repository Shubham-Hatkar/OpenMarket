package com.smartdevelopers.OpenMarket.Controller;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.AddSellerRequestDto;
import com.smartdevelopers.OpenMarket.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController
{

    @Autowired
    SellerService sellerService;

    // Add seller
    @PostMapping("/add")
    public String addSeller(@RequestBody AddSellerRequestDto addSellerRequestDto)
    {
        try
        {
            sellerService.addSeller(addSellerRequestDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Seller registered successfully.";
    }

    // Get all sellers
    // Get seller by aadharcard
    // Find seller of a particular age
}
