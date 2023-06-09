package com.smartdevelopers.OpenMarket.Repository;


import com.smartdevelopers.OpenMarket.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer>
{

}
