package com.smartdevelopers.OpenMarket.Convertors;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.CustomerRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.CustomerResponseDto;
import com.smartdevelopers.OpenMarket.Model.Customer;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConvertor
{
    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto)
    {
        Customer customer = Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .email(customerRequestDto.getEmail())
                .build();
        return customer;
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer)
    {
        CustomerResponseDto customerResponseDto = CustomerResponseDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .build();
        return customerResponseDto;
    }
}
