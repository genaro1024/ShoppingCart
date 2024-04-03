package com.gams.shoppingcart.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.gams.shoppingcart.api.entity.PurchaseOrder;

public interface OrderRepository extends CrudRepository<PurchaseOrder,Long> {

}
