package com.smartdevelopers.OpenMarket.Repository;

import com.smartdevelopers.OpenMarket.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
