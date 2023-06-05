package com.smartdevelopers.OpenMarket.Repository;

import com.smartdevelopers.OpenMarket.Model.Item;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
