package com.gams.shoppingcart.api.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class PurchaseOrder {
    @Id
    private long id;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List<Detail> details;

    @OneToOne(fetch = FetchType.LAZY)
    private Pay pay;

}
