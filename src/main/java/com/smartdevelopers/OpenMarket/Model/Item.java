package com.smartdevelopers.OpenMarket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Getter + @Setter + @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private  int requiredQuantity;

    @ManyToOne
    @JoinColumn
    Cart cart;

    @OneToOne
    @JoinColumn
    Product product;

    @ManyToOne
    @JoinColumn
    Ordered order;
}
