package com.smartdevelopers.OpenMarket.Controller;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.OrderRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.OrderResponseDto;
import com.smartdevelopers.OpenMarket.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController
{

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto)
    {
        OrderResponseDto orderResponseDto;
        try
        {
            orderResponseDto = orderService.placeOrder(orderRequestDto);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(orderResponseDto, HttpStatus.FOUND);
    }
}
