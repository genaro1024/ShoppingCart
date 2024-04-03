package com.gams.shoppingcart.api.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Pay {

    @Id
    private Long id;
    private Date date;
    private float amount;
    private boolean paid;
    private String voucher;


    @OneToOne(fetch = FetchType.EAGER)
    private PurchaseOrder order;

}
