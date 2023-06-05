package com.smartdevelopers.OpenMarket.Controller;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.AddProductRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.AddProductResponseDto;
import com.smartdevelopers.OpenMarket.Enum.ProductCategory;
import com.smartdevelopers.OpenMarket.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody AddProductRequestDto addProductRequestDto)
    {
        AddProductResponseDto addProductResponseDto;
        try
        {
            addProductResponseDto = productService.addProduct(addProductRequestDto);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(addProductResponseDto, HttpStatus.FOUND);
    }

    @GetMapping("/get/category/{productCategory}")
    public ResponseEntity getAllProductsByCategory(@PathVariable("productCategory") ProductCategory productCategory)
    {
        List<AddProductResponseDto> productResponseDtoList;
        try
        {
            productResponseDtoList = productService.getAllProductsByCategory(productCategory);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(productResponseDtoList, HttpStatus.FOUND);
    }
}
