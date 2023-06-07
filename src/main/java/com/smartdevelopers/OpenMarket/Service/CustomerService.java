package com.smartdevelopers.OpenMarket.Service;

import com.smartdevelopers.OpenMarket.Convertors.CustomerConvertor;
import com.smartdevelopers.OpenMarket.DTO.RequestDto.CustomerRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.CustomerResponseDto;
import com.smartdevelopers.OpenMarket.Exceptions.CustomerAlreadyExistException;
import com.smartdevelopers.OpenMarket.Exceptions.CustomerDoesNotExistException;
import com.smartdevelopers.OpenMarket.Exceptions.InvalidEmailException;
import com.smartdevelopers.OpenMarket.Model.Cart;
import com.smartdevelopers.OpenMarket.Model.Customer;
import com.smartdevelopers.OpenMarket.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService
{
    @Autowired
    CustomerRepository customerRepository;

    public void addCustomer(CustomerRequestDto customerRequestDto) throws CustomerAlreadyExistException
    {
        Customer customer = CustomerConvertor.customerRequestDtoToCustomer(customerRequestDto);
        // allocate cart to customer
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        // set cart in customer
        customer.setCart(cart);

        try
        {
            customerRepository.save(customer);
        }
        catch (Exception e)
        {
            throw new CustomerAlreadyExistException();
        }
    }

    public CustomerResponseDto getCustomerById(int customerId) throws CustomerDoesNotExistException
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
        CustomerResponseDto customerResponseDto = CustomerConvertor.customerToCustomerResponseDto(customer);
        return customerResponseDto;
    }

    public void deleteCustomerById(int customerId) throws CustomerDoesNotExistException {
        Customer customer;
        try
        {
            customer = customerRepository.findById(customerId).get();
            customerRepository.deleteById(customerId);
        }
        catch (Exception e)
        {
            throw new CustomerDoesNotExistException();
        }
    }

    public List<CustomerResponseDto> getAllCustomer()
    {
        List<CustomerResponseDto> customers = new ArrayList<>();
        List<Customer> customerList = customerRepository.findAll();
        for(Customer customer : customerList)
        {
            CustomerResponseDto customerResponseDto = CustomerConvertor.customerToCustomerResponseDto(customer);
            customers.add(customerResponseDto);
        }
        return customers;
    }

    public CustomerResponseDto getCustomerByEmail(String email) throws InvalidEmailException {
        Customer customer;
        try
        {
            customer = customerRepository.findByEmail(email);
        }
        catch (Exception e)
        {
            throw new InvalidEmailException();
        }
        CustomerResponseDto customerResponseDto = CustomerConvertor.customerToCustomerResponseDto(customer);
        return customerResponseDto;
    }

    public void updateCustomer(int customerId, CustomerRequestDto customerRequestDto) throws CustomerDoesNotExistException {
        Customer customer;
        try
        {
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e)
        {
            throw new CustomerDoesNotExistException();
        }
        customer.setAge(customerRequestDto.getAge());
        customer.setName(customerRequestDto.getName());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setMobNo(customerRequestDto.getMobNo());
        customerRepository.save(customer);
    }
}
