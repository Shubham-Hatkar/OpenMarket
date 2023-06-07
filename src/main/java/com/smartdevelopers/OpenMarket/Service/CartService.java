package com.smartdevelopers.OpenMarket.Service;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.OrderRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.OrderResponseDto;
import com.smartdevelopers.OpenMarket.Enum.ProductStatus;
import com.smartdevelopers.OpenMarket.Exceptions.CustomerDoesNotExistException;
import com.smartdevelopers.OpenMarket.Exceptions.InsufficientQuantityException;
import com.smartdevelopers.OpenMarket.Exceptions.ProductNotFoundException;
import com.smartdevelopers.OpenMarket.Model.*;
import com.smartdevelopers.OpenMarket.Repository.CustomerRepository;
import com.smartdevelopers.OpenMarket.Repository.ItemRepository;
import com.smartdevelopers.OpenMarket.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService
{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    ItemRepository itemRepository;


    public void addToCart(OrderRequestDto orderRequestDto) throws ProductNotFoundException, InsufficientQuantityException, CustomerDoesNotExistException {
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

        Cart cart = customer.getCart();
        int newCost = cart.getCartTotal() + (orderRequestDto.getRequiredQuantity() * product.getPrice());
        cart.setCartTotal(newCost);

        // check if item is already present in cart
        Item item = null;
        List<Item> itemList = itemRepository.findAll();
        for(Item item1 : itemList)
        {
            if(item1.getProduct().getId() == product.getId())
            {
                item = item1;
                break;
            }
        }

        // add item to current cart
        if(item == null)  item = new Item();

        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItemList().add(item);

        customerRepository.save(customer); // save the parent
    }

    public List<OrderResponseDto> checkOutCart(int customerId) throws CustomerDoesNotExistException, InsufficientQuantityException, ProductNotFoundException {
        // check customer is exist or not
        Customer customer;
        try
        {
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e)
        {
            throw new CustomerDoesNotExistException();
        }

        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        Cart cart = customer.getCart();
        List<Item> itemList = cart.getItemList();

        for(Item item : itemList)
        {
            if(item.getRequiredQuantity() <= item.getProduct().getQuantity())
            {
                Ordered order = new Ordered();
                order.setTotalCost(item.getRequiredQuantity() * item.getProduct().getPrice());
                order.setDeliveryCharge(0);
                order.setCustomer(customer);
                order.getItemList().add(item);

                Card card = customer.getCardList().get(0);
                String cardNo = "";
                for(int i = 0; i < card.getCardNo().length() - 4; i++) cardNo += "X";
                cardNo += card.getCardNo().substring(card.getCardNo().length() - 4);
                order.setCardUsedForPayment(cardNo);

                customer.getOrderedList().add(order);

                // update in product table
                Product product = item.getProduct();
                product.setQuantity(product.getQuantity() - item.getRequiredQuantity());
                if(product.getQuantity() == 0)
                {
                    product.setProductStatus(ProductStatus.OUT_OF_STOCK);
                }

                // update in item table
                //itemRepository.deleteById(item.getId());

                // responseDto
                OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                        .productName(item.getProduct().getName())
                        .orderDate(order.getOrderDate())
                        .orderedQuantity(item.getRequiredQuantity())
                        .cardUsedForPayment(cardNo)
                        .itemPrice(item.getProduct().getPrice())
                        .totalCost(order.getTotalCost())
                        .deliveryCharge(50)
                        .build();

                orderResponseDtoList.add(orderResponseDto);
            }
            else throw new InsufficientQuantityException();
        }


        cart.setItemList(new ArrayList<>());
        cart.setCartTotal(0);
        customerRepository.save(customer);

        return orderResponseDtoList;
    }
}
