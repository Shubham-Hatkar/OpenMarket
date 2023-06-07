package com.smartdevelopers.OpenMarket.Service;

import com.smartdevelopers.OpenMarket.Convertors.ProductConvertor;
import com.smartdevelopers.OpenMarket.DTO.RequestDto.ProductRequestDto;
import com.smartdevelopers.OpenMarket.DTO.ResponseDto.ProductResponseDto;
import com.smartdevelopers.OpenMarket.Enum.ProductCategory;
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

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerId {
        Seller seller;
        try
        {
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e)
        {
            throw new InvalidSellerId();
        }

        Product product = ProductConvertor.addProductRequestToSeller(productRequestDto);
        product.setSeller(seller);

        seller.getProductList().add(product);

        sellerRepository.save(seller);

        ProductResponseDto productResponseDto =
                ProductConvertor.productToProductResponseDto(product);

        return productResponseDto;
    }

    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory productCategory) throws InvalidCategoryException {
        List<Product> productList;
        try
        {
            productList = productRepository.findByProductCategory(productCategory);
        }
        catch (Exception e)
        {
            throw new InvalidCategoryException();
        }

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product : productList)
        {
            ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }
}
