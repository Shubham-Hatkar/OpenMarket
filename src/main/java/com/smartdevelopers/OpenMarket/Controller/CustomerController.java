package com.smartdevelopers.OpenMarket.Controller;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.CustomerRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.CustomerResponseDto;
import com.smartdevelopers.OpenMarket.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto)
    {
        try
        {
            customerService.addCustomer(customerRequestDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Customer registered successfully";
    }

    // get customer by id
    @GetMapping("/customerbyid")
    public ResponseEntity getCustomerById(@RequestParam("id") int customerId)
    {
        CustomerResponseDto customerResponseDto;
        try {
            customerResponseDto = customerService.getCustomerById(customerId);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerResponseDto, HttpStatus.NOT_FOUND);
    }

    // delete customer by id
    @DeleteMapping("/delete/{id}")
    public String deleteCustomerById(@PathVariable("id") int customerId)
    {
        try
        {
            customerService.deleteCustomerById(customerId);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Customer deleted successfully.";
    }


    // view all customer
    @GetMapping("/allcustomer")
    public List<CustomerResponseDto> getAllCustomer()
    {
        return customerService.getAllCustomer();
    }

    // get a customer by email
    @GetMapping("/byemail")
    public ResponseEntity getCustomerByEmail(@RequestParam("email") String email)
    {
        CustomerResponseDto customerResponseDto;
        try
        {
            customerResponseDto = customerService.getCustomerByEmail(email);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerResponseDto, HttpStatus.FOUND);
    }

    // update customer
    @PutMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") int customerId,
                                 @RequestBody CustomerRequestDto customerRequestDto)
    {
        try
        {
            customerService.updateCustomer(customerId, customerRequestDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Customer updated successfully";
    }
}
