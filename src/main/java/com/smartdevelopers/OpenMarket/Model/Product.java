package com.smartdevelopers.OpenMarket.Model;

import com.smartdevelopers.OpenMarket.Enum.ProductCategory;
import com.smartdevelopers.OpenMarket.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Getter + @Setter + @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String name;

    private int price;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    Item item;



}
