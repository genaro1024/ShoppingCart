package com.gams.shoppingcart.api.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Customer {
    
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PurchaseOrder> orders;
    

}
