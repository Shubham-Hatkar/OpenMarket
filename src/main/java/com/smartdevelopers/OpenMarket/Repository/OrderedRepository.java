package com.smartdevelopers.OpenMarket.Repository;

import com.smartdevelopers.OpenMarket.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, Integer> {
}
