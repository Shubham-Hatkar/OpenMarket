package com.smartdevelopers.OpenMarket.Controller;

import com.smartdevelopers.OpenMarket.DTO.RequestDto.ProductRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.ProductResponseDto;
import com.smartdevelopers.OpenMarket.Enum.ProductCategory;
import com.smartdevelopers.OpenMarket.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto)
    {
        ProductResponseDto productResponseDto;
        try
        {
            productResponseDto = productService.addProduct(productRequestDto);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(productResponseDto, HttpStatus.FOUND);
    }

    @GetMapping("/get/category/{productCategory}")
    public ResponseEntity getAllProductsByCategory(@PathVariable("productCategory") ProductCategory productCategory)
    {
        List<ProductResponseDto> productResponseDtoList;
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
