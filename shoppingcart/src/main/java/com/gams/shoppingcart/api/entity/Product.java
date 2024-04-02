package com.gams.shoppingcart.api.entity;

import lombok.Data;

@Data
public class Product {
    private long id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
}
