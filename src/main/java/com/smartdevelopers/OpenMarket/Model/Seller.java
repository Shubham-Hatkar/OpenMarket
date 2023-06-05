package com.smartdevelopers.OpenMarket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data // @Getter + @Setter + @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "seller")
public class Seller
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    private String mobNo;

    @Column(unique = true)
    private String aadharNo;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    List<Product> productList = new ArrayList<>();
}
