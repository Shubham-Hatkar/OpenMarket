package com.smartdevelopers.OpenMarket.Repository;

import com.smartdevelopers.OpenMarket.Enum.ProductCategory;
import com.smartdevelopers.OpenMarket.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductCategory(ProductCategory productCategory);
}
