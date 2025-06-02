package com.spring.security.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
public class Product {

    @Id
    private int id;
    private String name;
    private int prize;

    // Add this constructor
    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.prize = price;
    }

    // Add no-args constructor (required by JPA)
    public Product() {
    }

}
