package com.smartdevelopers.OpenMarket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Data // @Getter + @Setter + @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String name;

    private int age;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobNo;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Card> cardList = new ArrayList<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Ordered> orderedList = new ArrayList<>();

}
