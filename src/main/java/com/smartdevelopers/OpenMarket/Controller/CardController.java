package com.smartdevelopers.OpenMarket.Controller;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.CardRequestDto;
import com.smartdevelopers.OpenMarket.DTO.RequestDto.DeleteCardRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.CardDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.CardResponseDto;
import com.smartdevelopers.OpenMarket.Model.Customer;
import com.smartdevelopers.OpenMarket.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController
{
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody  CardRequestDto cardRequestDto)
    {
        CardResponseDto cardResponseDto;
        try
        {
            cardResponseDto = cardService.addCard(cardRequestDto);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(cardResponseDto, HttpStatus.FOUND);
    }

    // remove card delete
    @DeleteMapping("/cardtype")
    public String deleteCard(@RequestBody DeleteCardRequestDto deleteCardRequestDto)
    {
        try
        {
            cardService.deleteCard(deleteCardRequestDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Card deleted successfully";
    }


    // remove all cards of particular customer id
    @DeleteMapping("/delete/{id}")
    public String deleteAllCardByCustomerId(@PathVariable("id") int customerId)
    {
        Customer customer;
        try
        {
            customer = cardService.deleteAllCardByCustomerId(customerId);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Hi " + customer.getName() + ", All cards are deleted";
    }

    @GetMapping("/allcards/{id}")
    public ResponseEntity getAllCardsByCustomerId(@PathVariable("id") int customerId)
    {
        List<CardDto> cardResponseDtoList;
        try {
            cardResponseDtoList = cardService.getAllCardsByCustomerId(customerId);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(cardResponseDtoList, HttpStatus.FOUND);
    }
}
