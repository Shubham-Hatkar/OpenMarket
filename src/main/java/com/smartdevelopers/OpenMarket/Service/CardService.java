package com.smartdevelopers.OpenMarket.Service;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.CardRequestDto;
import com.smartdevelopers.OpenMarket.DTO.RequestDto.DeleteCardRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.CardDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.CardResponseDto;
import com.smartdevelopers.OpenMarket.Exceptions.CardAlreadyExistException;
import com.smartdevelopers.OpenMarket.Exceptions.CardDoesNotExistException;
import com.smartdevelopers.OpenMarket.Exceptions.CustomerDoesNotExistException;
import com.smartdevelopers.OpenMarket.Model.Card;
import com.smartdevelopers.OpenMarket.Model.Customer;
import com.smartdevelopers.OpenMarket.Repository.CardRepository;
import com.smartdevelopers.OpenMarket.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService
{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerDoesNotExistException, CardAlreadyExistException {
        Customer customer;
        try
        {
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new CustomerDoesNotExistException();
        }

        Card card = Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .customer(customer) //setting customer.
                .build();

        // adding card into cardlist of customer
        customer.getCardList().add(card);

        // Card number is unique so add exception
        try
        {
            customerRepository.save(customer);
        }
        catch (Exception e)
        {
            throw new CardAlreadyExistException();
        }

        // create responseDto
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setCustomerName(customer.getName());


        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card card1 : customer.getCardList())
        {
            CardDto cardDto = new CardDto();
            cardDto.setCardType(card1.getCardType());
            cardDto.setCardNo(card1.getCardNo());
            cardDtoList.add(cardDto);
        }
        cardResponseDto.setCards(cardDtoList);
        return cardResponseDto;
    }

    public void deleteCard(DeleteCardRequestDto deleteCardRequestDto) throws CustomerDoesNotExistException, CardDoesNotExistException {
        Customer customer;
        try
        {
            customer = customerRepository.findById(deleteCardRequestDto.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new CustomerDoesNotExistException();
        }

        List<Card> cardList = customer.getCardList();
        boolean flag = false;
        for (Card card : cardList)
        {
            if(card.getCardType().equals(deleteCardRequestDto.getCardType()))
            {
                cardList.remove(card);
                cardRepository.deleteById(card.getId()); // delete card from card table
                flag = true;
                break;
            }
        }
        if(flag == false) throw new CardDoesNotExistException();

        customerRepository.save(customer);
    }

    public Customer deleteAllCardByCustomerId(int customerId) throws CustomerDoesNotExistException
    {

        Customer customer;
        try
        {
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e)
        {
            throw new CustomerDoesNotExistException();
        }
        // Delete from card table
        List<Card> cardList = cardRepository.findAll();
        for(Card card : cardList)
        {
            if(card.getCustomer().getId() == customerId)
            {
                cardRepository.delete(card);
            }
        }

        // clear the customer cardList
        customer.getCardList().clear();
        customerRepository.save(customer);
        return customer;
    }


    public List<CardDto> getAllCardsByCustomerId(int customerId) throws CustomerDoesNotExistException {
        List<Card> cardList;
        try
        {
            cardList = customerRepository.findById(customerId).get().getCardList();
        }
        catch (Exception e)
        {
            throw new CustomerDoesNotExistException();
        }
        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card card : cardList)
        {
            CardDto cardDto = new CardDto();
            cardDto.setCardType(card.getCardType());
            cardDto.setCardNo(card.getCardNo());
            cardDtoList.add(cardDto);
        }
        return cardDtoList;
    }
}
