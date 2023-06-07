package com.smartdevelopers.OpenMarket.Controller;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.OrderRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.OrderResponseDto;
import com.smartdevelopers.OpenMarket.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController
{

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestBody OrderRequestDto orderRequestDto)
    {
        try
        {
            cartService.addToCart(orderRequestDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Item added into cart.";
    }

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity checkOutCart(@PathVariable("customerId") int customerId)
    {
        List<OrderResponseDto> orderResponseDtoList;
        try
        {
            orderResponseDtoList = cartService.checkOutCart(customerId);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(orderResponseDtoList, HttpStatus.FOUND);
    }
}
