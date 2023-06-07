package com.smartdevelopers.OpenMarket.Service;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.OrderRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.OrderResponseDto;
import com.smartdevelopers.OpenMarket.Enum.ProductStatus;
import com.smartdevelopers.OpenMarket.Exceptions.CustomerDoesNotExistException;
import com.smartdevelopers.OpenMarket.Exceptions.InsufficientQuantityException;
import com.smartdevelopers.OpenMarket.Exceptions.ProductNotFoundException;
import com.smartdevelopers.OpenMarket.Model.*;
import com.smartdevelopers.OpenMarket.Repository.CustomerRepository;
import com.smartdevelopers.OpenMarket.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class OrderService
{
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    EmailService emailService;


    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerDoesNotExistException, ProductNotFoundException, InsufficientQuantityException {
        Customer customer;
        try
        {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new CustomerDoesNotExistException();
        }

        Product product;
        try
        {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new ProductNotFoundException();
        }

        if(product.getQuantity() < orderRequestDto.getRequiredQuantity())
        {
            throw new InsufficientQuantityException();
        }

        Ordered order = new Ordered();
        int totalCost = orderRequestDto.getRequiredQuantity() * product.getPrice();
        int deliveryCharge = 0;
        if(totalCost < 500)
        {
            deliveryCharge = 50;
            totalCost += totalCost + deliveryCharge;
        }
        order.setTotalCost(totalCost);
        order.setDeliveryCharge(deliveryCharge);

        Card card = customer.getCardList().get(0);
        String cardNo = "";
        for(int i = 0; i < card.getCardNo().length() - 4; i++) cardNo += "X";
        cardNo += card.getCardNo().substring(card.getCardNo().length() - 4);
        order.setCardUsedForPayment(cardNo);

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrder(order);
        order.getItemList().add(item);
        order.setCustomer(customer);

        int leftQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();
        if(leftQuantity <= 0)
        {
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        product.setQuantity(leftQuantity);

        customer.getOrderedList().add(order);
        Customer savedCustomer =  customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrderedList().get(savedCustomer.getOrderedList().size() - 1);


        // Send an email
        String subject = "Order Placed Successfully";
        String message = "Congrats your order with total value " + totalCost + " has been placed.";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("openmarket@gmail.com");
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject(subject);
        emailService.sendEmail(simpleMailMessage);

        //emailService.sendEmail(customer.getEmail(),subject,message);

        //prepare ResponseDto
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getName())
                .orderDate(savedOrder.getOrderDate())
                .orderedQuantity(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardNo)
                .itemPrice(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(50)
                .build();

        return orderResponseDto;
    }
}
