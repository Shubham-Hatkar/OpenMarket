package com.smartdevelopers.OpenMarket.Service;

import com.smartdevelopers.OpenMarket.Convertors.ProductConvertor;
import com.smartdevelopers.OpenMarket.DTO.RequestDto.AddProductRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.AddProductResponseDto;
import com.smartdevelopers.OpenMarket.Enum.ProductCategory;
import com.smartdevelopers.OpenMarket.Enum.ProductStatus;
import com.smartdevelopers.OpenMarket.Exceptions.InvalidCategoryException;
import com.smartdevelopers.OpenMarket.Exceptions.InvalidSellerId;
import com.smartdevelopers.OpenMarket.Model.Product;
import com.smartdevelopers.OpenMarket.Model.Seller;
import com.smartdevelopers.OpenMarket.Repository.ProductRepository;
import com.smartdevelopers.OpenMarket.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService
{

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    public AddProductResponseDto addProduct(AddProductRequestDto addProductRequestDto) throws InvalidSellerId {
        Seller seller;
        try
        {
            seller = sellerRepository.findById(addProductRequestDto.getSellerId()).get();
        }
        catch (Exception e)
        {
            throw new InvalidSellerId();
        }

        Product product = ProductConvertor.addProductRequestToSeller(addProductRequestDto);
        product.setSeller(seller);

        seller.getProductList().add(product);

        sellerRepository.save(seller);

        AddProductResponseDto addProductResponseDto =
                ProductConvertor.productToProductResponseDto(product);

        return addProductResponseDto;
    }

    public List<AddProductResponseDto> getAllProductsByCategory(ProductCategory productCategory) throws InvalidCategoryException {
        List<Product> productList;
        try
        {
            productList = productRepository.findByProductCategory(productCategory);
        }
        catch (Exception e)
        {
            throw new InvalidCategoryException();
        }

        List<AddProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product : productList)
        {
            AddProductResponseDto addProductResponseDto = ProductConvertor.productToProductResponseDto(product);
            productResponseDtoList.add(addProductResponseDto);
        }
        return productResponseDtoList;
    }
}
