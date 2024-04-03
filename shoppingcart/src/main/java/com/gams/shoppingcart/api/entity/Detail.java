package com.gams.shoppingcart.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class Detail {

    @Id
    private long id;
    private long productId;
    private float quantity;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private PurchaseOrder order;



}
