package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "baskets")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}
