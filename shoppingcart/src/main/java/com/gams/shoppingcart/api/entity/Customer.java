package com.gams.shoppingcart.api.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Customer {

    private long id;
    private String login;
    private String pass;


}
