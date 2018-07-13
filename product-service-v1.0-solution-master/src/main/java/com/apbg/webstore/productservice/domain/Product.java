package com.apbg.webstore.productservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;

    String image;

    Double price;

    @Column(columnDefinition = "TEXT")
    String description;
}
